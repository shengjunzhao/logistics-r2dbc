package com.haole.logistics.r2dbc.service.snowflake;

import com.haole.logistics.r2dbc.common.BusinessException;
import com.haole.logistics.r2dbc.dal.dao.SnowflakeWorkerMapper;
import com.haole.logistics.r2dbc.dal.dao.SnowflakeWorkerRecordMapper;
import com.haole.logistics.r2dbc.dal.model.SnowflakeWorker;
import com.haole.logistics.r2dbc.dal.model.SnowflakeWorkerRecord;
import com.haole.logistics.r2dbc.enums.ResultType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.*;
import java.util.Date;
import java.util.function.Function;


public class SnowflakeGenerator implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SnowflakeGenerator.class);

    private static final long START_TIME = LocalDate.of(2024, 5, 26).atStartOfDay(ZoneOffset.ofHours(8)).toInstant().getEpochSecond();
    private static final long CLOCK_LANE_BITS = 1L;
    private static final long SEQUENCE_BITS = 21L;
    private static final long WORKER_ID_BITS = 8L;
    private static final long TIME_LEFT_SHIFT_BITS = 30L;
    private static final long CLOCK_LANE_LEFT_SHIFT_BITS = 29L;
    private static final long CLOCK_LANE_MASK = 1L;
    private static final long SEQUENCE_MASK = (2 << (SEQUENCE_BITS - 1)) - 1L;  //2097151L;
    public static final long MAX_WORKER_ID = 255L;
    private long clockLane = 0L;
    private long lastTime = 0L;
    private long workerId = 0L;
    private long sequence = 0L;
    private long currentId = 0L;
    @Value("${server.port}")
    private Integer port;

    private final SnowflakeWorkerMapper snowflakeWorkerMapper;

    private final SnowflakeWorkerRecordMapper snowflakeWorkerRecordMapper;

    public SnowflakeGenerator(SnowflakeWorkerMapper snowflakeWorkerMapper, SnowflakeWorkerRecordMapper snowflakeWorkerRecordMapper) {
        this.snowflakeWorkerMapper = snowflakeWorkerMapper;
        this.snowflakeWorkerRecordMapper = snowflakeWorkerRecordMapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        getWorkerId().subscribe();
    }


//    protected SnowflakeGenerator(int workerId) {
//        if (workerId <= MAX_WORKER_ID && workerId >= 0L) {
//            this.workerId = workerId;
//        } else {
//            throw new IllegalArgumentException(String.format("workerId can't be greater than {} or less than 0", 255L));
//        }
//    }

    public synchronized long nextId() {
        long currentTime = System.currentTimeMillis() / 1000L;
        long lastTime = this.lastTime;
        if (currentTime > lastTime) {
            this.sequence = 0L;
        } else if (currentTime == lastTime) {
            this.sequence = (this.sequence + 1L) & SEQUENCE_MASK;
            logger.info("sequence={}", this.sequence);
        } else {
            this.sequence = 0L;
            this.clockLane = this.clockLane + CLOCK_LANE_BITS & CLOCK_LANE_MASK;
            logger.warn("时钟回拨 clockLane: {}", this.clockLane);
        }

        this.lastTime = currentTime;
        long time = currentTime - START_TIME;
        this.currentId = time << TIME_LEFT_SHIFT_BITS | this.clockLane << CLOCK_LANE_LEFT_SHIFT_BITS | this.sequence << WORKER_ID_BITS | this.workerId;
        logger.info("currentTime={},lastTime={},sequence={},currentId={}", currentTime, lastTime, this.sequence, this.currentId);
        return this.currentId;
    }

    public Mono<Long> reactiveNextId() {
        return Mono.just(nextId());
    }

    public long currentId() {
        return this.currentId;
    }

    public static long getSequence(long id) {
        return id >> WORKER_ID_BITS & SEQUENCE_MASK;
    }

    public static long getWorkerId(long id) {
        return id & MAX_WORKER_ID;
    }

    public static long getClockLane(long id) {
        return id >> CLOCK_LANE_LEFT_SHIFT_BITS & CLOCK_LANE_MASK;
    }

    public static Date getDate(long id) {
        Instant instant = getInstant(id);
        return Date.from(instant);
    }

    public static Instant getInstant(long id) {
        return Instant.ofEpochSecond((id >> TIME_LEFT_SHIFT_BITS) + START_TIME);
    }

    public static LocalDateTime getLocalDateTime(long id) {
        Instant instant = getInstant(id);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }


    private Mono<Void> getWorkerId() {
        Mono<SnowflakeWorker> snowflakeWorker = snowflakeWorkerMapper.findById(SnowflakeWorkerConstants.DEFAULT_GROUP)
                .onErrorMap(original -> new BusinessException(ResultType.FAILURE.code(), original.getMessage(), original));

        return snowflakeWorker.flatMap(new Function<SnowflakeWorker, Mono<SnowflakeWorker>>() {
                    @Override
                    public Mono<SnowflakeWorker> apply(SnowflakeWorker s) {
                        long workId = (s.getLatestWorkerId() + 1) & MAX_WORKER_ID;
                        Long version = s.getDataVersion() + 1L;
                        s.setDataVersion(version);
                        s.setLatestWorkerId((int) workId);
                        return Mono.just(s);
                    }
                })
                .flatMap(snowflakeWorkerMapper::save)
                .flatMap(s -> {
                    this.workerId = s.getLatestWorkerId();
                    InetAddress inetAddress = null;
                    try {
                        inetAddress = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        throw new RuntimeException(e);
                    }
                    String ip = inetAddress.getHostAddress();
                    String hostName = inetAddress.getHostName();
                    SnowflakeWorkerRecord record = new SnowflakeWorkerRecord();
                    record.setWorkerId((int) workerId);
                    record.setHostAddress(ip);
                    record.setHostName(hostName);
                    record.setServerPort(this.port);
                    return Mono.just(record);
                })
                .flatMap(snowflakeWorkerRecordMapper::save)
                .then();



//        snowflakeWorker.subscribe(s -> {
//            long workId = (s.getLatestWorkerId() + 1) & MAX_WORKER_ID;
//            Long version = s.getDataVersion() + 1L;
//            s.setDataVersion(version);
//            s.setLatestWorkerId((int) workId);
//            snowflakeWorkerMapper.save(s).subscribe(sa -> {
//                logger.info("group_name={}", sa.getGroupName());
//                this.workerId = workId;
//                try {
//                    recordWorkerId(workId);
//                } catch (UnknownHostException e) {
//                    throw new RuntimeException(e);
//                }
//            });
//        });
//        snowflakeWorker.flatMap()
//        snowflakeWorker.thenEmpty()
//        String querySql = SnowflakeWorker.QUERY_SQL;
//        Map<String, Object> map = this.jdbcTemplate.queryForMap(querySql, new Object[]{this.defaultGroup});
//        long nextWorkerId = (long)((Integer)map.get("latest_worker_id") + 1) & 255L;
//        Long version = (Long)map.get("data_version");
//        String updateSql = SnowflakeWorker.UPDATE_SQL;
//        int result = this.jdbcTemplate.update(updateSql, new Object[]{nextWorkerId, version + 1L, this.defaultGroup, version});
//        if (result > 0) {
//            return nextWorkerId;
//        } else {
//            TimeUnit.SECONDS.sleep(1L);
//            return this.getWorkerId();
//        }
    }


    private void recordWorkerId(long workerId) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String ip = inetAddress.getHostAddress();
        String hostName = inetAddress.getHostName();
        SnowflakeWorkerRecord record = new SnowflakeWorkerRecord();
        record.setWorkerId((int) workerId);
        record.setHostAddress(ip);
        record.setHostName(hostName);
        record.setServerPort(this.port);
        snowflakeWorkerRecordMapper.save(record).subscribe(sa -> logger.info("recordId={}", sa.getRecordId()));

//        String inserSql = "INSERT INTO `snowflake_worker_record` (`worker_id`, `host_address`, `host_name`, `server_port`) VALUES (?, ?, ?, ?)";
//        this.jdbcTemplate.update(inserSql, new Object[]{workerId, ip, hostName, this.port});
    }
}
