package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.WayBillAddress;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WayBillAddressMapper extends ReactiveCrudRepository<WayBillAddress,Long> {
}
