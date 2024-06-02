package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.SnowflakeWorker;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

//@Repository
public interface SnowflakeWorkerMapper extends ReactiveCrudRepository<SnowflakeWorker, String> {


}
