package com.haole.logistics.r2dbc.controller.param.waybill;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: DriverWayQueryParam
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/5/31 20:49
 */
public class DriverWayQueryParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -4073695066427333501L;

    @NotBlank(message="请提供手机号")
    private String mobileNo;

    /**
     * 1待接单,2待出发,3:待送达,4:已签收
     * 1;10,20;30,31;40
     */
    private Integer wayBillStatus;

    /**
     * 运单编号
     */
    private String wayBillSn;
    private int pageNum = 1;
    private int pageSize = 10;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getWayBillStatus() {
        return wayBillStatus;
    }

    public void setWayBillStatus(Integer wayBillStatus) {
        this.wayBillStatus = wayBillStatus;
    }

    public String getWayBillSn() {
        return wayBillSn;
    }

    public void setWayBillSn(String wayBillSn) {
        this.wayBillSn = wayBillSn;
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
