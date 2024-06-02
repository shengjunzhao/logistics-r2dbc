package com.haole.logistics.r2dbc.dal.dao;

import com.haole.logistics.r2dbc.dal.model.LogisticsOrderBill;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

//@Repository
public interface LogisticsOrderBillMapper extends ReactiveCrudRepository<LogisticsOrderBill, Long>, ReactiveSortingRepository<LogisticsOrderBill, Long> {

    /**
     * 物流订单中sourceSystem、sourceBillSn、parentSourceBillNo、SourceBillSn是唯一的
     *
     * @param sourceSystem
     * @param sourceBillSn
     * @param parentSourceBillNo
     * @param sourceBillType
     * @return
     */
    @Query("select l.* from logistics.logistics_order_bill l where l.source_system=:sourceSystem and l.source_bill_sn=:sourceBillSn " +
            "and l.parent_source_bill_no=:parentSourceBillNo and l.source_bill_type=:sourceBillType")
    Mono<LogisticsOrderBill> findUniqueLogistics(String sourceSystem, String sourceBillSn, String parentSourceBillNo, Integer sourceBillType);


}
