package com.haole.logistics.r2dbc.controller.param.waybill;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: OpWayQueryParam
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/6/1 16:39
 */
public class OpWayQueryParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 9111281603849927426L;

    /**
     * 运单id
     */
    private Long wayBillId;

    /**
     * 运单编号
     */
    private String wayBillSn;
    /**
     * 物流单号(承运商运单号)
     */
    private String logisticsBillNo;

    /**
     * 运输方式,1:快递运输,2:零担运输,3:整车运输
     */

    private Integer transType;
    /**
     * 运单状态，1待接单,2已受理,3已拒绝,4,运输中,5已签收,6签收异常
     * 1;10;3;20,30,31;40;50
     * <p>
     * 1:待接单,2:已取消,3:已决绝,4:待审核
     * 10:已接单(待装车)
     * 20:揽件(待发车),21:揽件失败
     * 30:运输中(在途、待卸车),31:待签收
     * 40:签收
     * 50签收异常
     */
    private Integer wayBillStatus;
    /**
     * 来源单号
     */
    private String sourceBillSn;

    /**
     * 来源单类型 10手工发货,20下单
     */
    private Integer sourceBillType;

    /**
     * 承运人
     */
    private String carrier;
    /**
     * 发货市code
     */
    private String deliverCountyCode;


    /**
     * 收货市code
     */
    private String receiverCountyCode;

    private String sourceSystem;

    /**
     * 开始运单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String createTimeStart;

    /**
     * 截止运单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String createTimeEnd;

    /**
     * 发货方code
     */
    private String deliverEnterpriseCode;

    /**
     * 收货方code
     */
    private String receiverEnterpriseCode;
    /**
     * 发货方
     */
    private String deliverEnterpriseName;

    /**
     * 收货方
     */
    private String receiverEnterpriseName;

    /**
     *  运单轨迹是否存在     0 无轨迹   1 有轨迹
     */
    private Integer traceFlag;


    private int pageNum = 1;
    private int pageSize = 10;

    public Long getWayBillId() {
        return wayBillId;
    }

    public void setWayBillId(Long wayBillId) {
        this.wayBillId = wayBillId;
    }

    public String getWayBillSn() {
        return wayBillSn;
    }

    public void setWayBillSn(String wayBillSn) {
        this.wayBillSn = wayBillSn;
    }

    public String getLogisticsBillNo() {
        return logisticsBillNo;
    }

    public void setLogisticsBillNo(String logisticsBillNo) {
        this.logisticsBillNo = logisticsBillNo;
    }

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Integer getWayBillStatus() {
        return wayBillStatus;
    }

    public void setWayBillStatus(Integer wayBillStatus) {
        this.wayBillStatus = wayBillStatus;
    }

    public String getSourceBillSn() {
        return sourceBillSn;
    }

    public void setSourceBillSn(String sourceBillSn) {
        this.sourceBillSn = sourceBillSn;
    }

    public Integer getSourceBillType() {
        return sourceBillType;
    }

    public void setSourceBillType(Integer sourceBillType) {
        this.sourceBillType = sourceBillType;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getDeliverCountyCode() {
        return deliverCountyCode;
    }

    public void setDeliverCountyCode(String deliverCountyCode) {
        this.deliverCountyCode = deliverCountyCode;
    }

    public String getReceiverCountyCode() {
        return receiverCountyCode;
    }

    public void setReceiverCountyCode(String receiverCountyCode) {
        this.receiverCountyCode = receiverCountyCode;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(String createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public String getDeliverEnterpriseCode() {
        return deliverEnterpriseCode;
    }

    public void setDeliverEnterpriseCode(String deliverEnterpriseCode) {
        this.deliverEnterpriseCode = deliverEnterpriseCode;
    }

    public String getReceiverEnterpriseCode() {
        return receiverEnterpriseCode;
    }

    public void setReceiverEnterpriseCode(String receiverEnterpriseCode) {
        this.receiverEnterpriseCode = receiverEnterpriseCode;
    }

    public String getDeliverEnterpriseName() {
        return deliverEnterpriseName;
    }

    public void setDeliverEnterpriseName(String deliverEnterpriseName) {
        this.deliverEnterpriseName = deliverEnterpriseName;
    }

    public String getReceiverEnterpriseName() {
        return receiverEnterpriseName;
    }

    public void setReceiverEnterpriseName(String receiverEnterpriseName) {
        this.receiverEnterpriseName = receiverEnterpriseName;
    }

    public Integer getTraceFlag() {
        return traceFlag;
    }

    public void setTraceFlag(Integer traceFlag) {
        this.traceFlag = traceFlag;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
