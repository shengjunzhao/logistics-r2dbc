package com.haole.logistics.r2dbc.enums;

public enum WayBillStatusEnum {

    NOT_RECEIVED_ORDER(1, "待接单", "待接单"),
    CANCELED(2, "已取消", "已取消"),
    REFUSED(3, "已拒绝", "已拒绝"),
    AUDIT(4, "待审核", "待审核"),
    RECEIVED_ORDER(10, "已接单", "待装车"),
    PARCEL_SOLICITATION(20, "揽收", "待发车"),
    BID_FAILURE(21, "揽件失败", "揽件失败"),
    IN_TRANSIT(30, "在途", "待卸车"),
    WAIT_TO_BE_SIGNED(31, "待签收", "待签收"),
    SIGN_FOR(40, "签收", "签收"),
    ABNORMAL_SIGNING(50, "签收异常", "签收异常");


    WayBillStatusEnum(Integer type, String expressDesc, String ftLDesc) {
        this.type = type;
        this.expressDesc = expressDesc;
        this.ftLDesc = ftLDesc;
    }

    private Integer type;
    /**
     * 快递描述
     */
    private String expressDesc;
    /**
     * 司机小程序整车描述
     */
    private String ftLDesc;

    public Integer getType() {
        return type;
    }

    public String getExpressDesc() {
        return expressDesc;
    }

    public String getFtLDesc() {
        return ftLDesc;
    }

    public static String getExpressDescByType(Integer type) {

        for (WayBillStatusEnum e : WayBillStatusEnum.values()) {
            if (e.getType().equals(type)) {
                return e.expressDesc;
            }
        }
        return null;
    }

    public static String getFtlDescByType(Integer type) {

        for (WayBillStatusEnum e : WayBillStatusEnum.values()) {
            if (e.getType().equals(type)) {
                return e.ftLDesc;
            }
        }
        return null;
    }

    public static WayBillStatusEnum get(Integer type) {

        for (WayBillStatusEnum e : WayBillStatusEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }
}
