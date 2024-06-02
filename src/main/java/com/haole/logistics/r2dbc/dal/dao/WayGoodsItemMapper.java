package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.WayGoodsItem;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface WayGoodsItemMapper extends ReactiveCrudRepository<WayGoodsItem,Long> {
}
