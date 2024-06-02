package com.haole.logistics.r2dbc.service.log.impl;

import com.haole.logistics.r2dbc.common.OperLogConstants;
import com.haole.logistics.r2dbc.dal.dao.OperLogMapper;
import com.haole.logistics.r2dbc.dal.model.OperLog;
import com.haole.logistics.r2dbc.enums.DeleteFlag;
import com.haole.logistics.r2dbc.service.log.OperLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperLogServiceImpl implements OperLogService {

    private static final Logger logger = LoggerFactory.getLogger(OperLogServiceImpl.class);

    private OperLogMapper logMapper;

    @Override
    public void saveWayLog(Long wayBillId, String logType, String message, Long userId, Long enterpriseId) {
        OperLog log = new OperLog();
        log.setBusinessId(wayBillId);
        log.setLogClass(OperLogConstants.LOG_CLASS_WAYBILL);
        log.setLogType(logType);
        log.setContent(message);
        log.setCreateTime(LocalDateTime.now());
        log.setCreateByUserId(userId);
        log.setCreateByEnterpriseId(enterpriseId);
        log.setUpdateTime(LocalDateTime.now());
        log.setUpdateByUserId(userId);
        log.setUpdateByEnterpriseId(enterpriseId);
        log.setDeleteFlag(DeleteFlag.NOT_DELETED.getType());
        logMapper.save(log).subscribe(operLog -> logger.info("logId={}", operLog.getLogId()));
    }

    @Override
    public void saveLogisticsOrderLog(Long logisticsOrderId, String logType, String message, Long userId, Long enterpriseId) {
        OperLog log = new OperLog();
        log.setBusinessId(logisticsOrderId);
        log.setLogClass(OperLogConstants.LOG_CLASS_LOGISTICS_ORDER);
        log.setLogType(logType);
        log.setContent(message);
        log.setCreateTime(LocalDateTime.now());
        log.setCreateByUserId(userId);
        log.setCreateByEnterpriseId(enterpriseId);
        log.setUpdateTime(LocalDateTime.now());
        log.setUpdateByUserId(userId);
        log.setUpdateByEnterpriseId(enterpriseId);
        logMapper.save(log).subscribe(operLog -> logger.info("logId={}", operLog.getLogId()));
    }

    @Autowired
    public void setLogMapper(OperLogMapper logMapper) {
        this.logMapper = logMapper;
    }
}
