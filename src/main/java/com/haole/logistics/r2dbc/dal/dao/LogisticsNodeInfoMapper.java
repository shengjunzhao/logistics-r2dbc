package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.LogisticsNodeInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LogisticsNodeInfoMapper extends ReactiveCrudRepository<LogisticsNodeInfo,Long> {
}
