package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.OperLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OperLogMapper extends ReactiveCrudRepository<OperLog, Long> {
}
