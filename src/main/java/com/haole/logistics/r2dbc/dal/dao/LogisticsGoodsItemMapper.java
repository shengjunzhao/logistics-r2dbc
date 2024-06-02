package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.LogisticsGoodsItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LogisticsGoodsItemMapper extends ReactiveCrudRepository<LogisticsGoodsItem,Long> {
}
