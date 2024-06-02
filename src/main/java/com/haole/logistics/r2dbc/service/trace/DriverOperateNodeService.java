package com.haole.logistics.r2dbc.service.trace;

public interface DriverOperateNodeService {

    /**
     * 返回小程序中司机操作节点的描述
     * @param node
     * @return
     */
    String getOperateDes(Integer node);
}
