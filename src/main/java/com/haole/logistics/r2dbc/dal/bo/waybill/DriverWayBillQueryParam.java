package com.haole.logistics.r2dbc.dal.bo.waybill;

import java.io.Serial;
import java.io.Serializable;

/**
 * ClassName: DriverWayBillQueryParam
 *
 * @author shengjunzhao
 * @description:
 * @since 2024/5/30 12:10
 */
public class DriverWayBillQueryParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -8389480042080263628L;

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
