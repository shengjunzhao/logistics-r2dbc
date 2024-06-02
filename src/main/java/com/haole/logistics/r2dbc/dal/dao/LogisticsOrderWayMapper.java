package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.LogisticsOrderWay;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LogisticsOrderWayMapper extends ReactiveCrudRepository<LogisticsOrderWay,Long> {
}
