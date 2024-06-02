package com.haole.logistics.r2dbc.service.trace.impl;

import com.haole.logistics.r2dbc.service.trace.DriverOperateNodeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DriverOperateNodeServiceImpl implements DriverOperateNodeService {

    private static final Map<Integer, String> operateInfo = new HashMap<>();

    static {
        operateInfo.put(1, "运单创建");
        operateInfo.put(10, "【%s】已接单，司机【%s】");
        operateInfo.put(20, "【%s】，已完成装车");
        operateInfo.put(30, "【%s】，车辆已发车");
        operateInfo.put(31, "【%s】，已完成卸车");
        operateInfo.put(40, "【%s】，由本人签收");
    }

    @Override
    public String getOperateDes(Integer node) {
        return operateInfo.get(node);
    }
}
