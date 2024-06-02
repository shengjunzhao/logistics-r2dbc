package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.SnowflakeWorkerRecord;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

//@Repository
public interface SnowflakeWorkerRecordMapper extends ReactiveCrudRepository<SnowflakeWorkerRecord,Long> {
}
