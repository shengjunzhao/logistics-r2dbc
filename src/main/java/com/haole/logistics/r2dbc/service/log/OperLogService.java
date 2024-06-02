package com.haole.logistics.r2dbc.service.log;

import org.springframework.scheduling.annotation.Async;

public interface OperLogService {

    @Async
    void saveWayLog(Long wayBillId, String  logType, String message, Long userId, Long enterpriseId);

    @Async
    void saveLogisticsOrderLog(Long logisticsOrderId, String logType, String message, Long userId, Long enterpriseId);

}
