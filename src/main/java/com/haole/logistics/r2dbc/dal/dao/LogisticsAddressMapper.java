package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.LogisticsAddress;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LogisticsAddressMapper extends ReactiveCrudRepository<LogisticsAddress,Long> {
}
