package com.haole.logistics.r2dbc.config;

import com.haole.logistics.r2dbc.dal.dao.SnowflakeWorkerMapper;
import com.haole.logistics.r2dbc.dal.dao.SnowflakeWorkerRecordMapper;
import com.haole.logistics.r2dbc.service.snowflake.SnowflakeGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeConfigure {


    @Bean
    @ConditionalOnMissingBean
    SnowflakeGenerator snowflakeGenerator(SnowflakeWorkerMapper snowflakeWorkerMapper, SnowflakeWorkerRecordMapper snowflakeWorkerRecordMapper) {
        return new SnowflakeGenerator(snowflakeWorkerMapper, snowflakeWorkerRecordMapper);
    }


}
