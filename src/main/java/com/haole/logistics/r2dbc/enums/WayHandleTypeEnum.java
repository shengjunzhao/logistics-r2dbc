package com.haole.logistics.r2dbc.enums;

public enum WayHandleTypeEnum {

    KD100(1, "快递100"),//快递运输
    MP(2, "物流小程序"),//零担运输
    SUPPLIER(3, "供应商运力"),
    CARRIER(4, "承运商"),
    ;


    WayHandleTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


    public static String getByType(Integer type) {

        for (WayHandleTypeEnum e : WayHandleTypeEnum.values()) {
            if (e.getType().equals(type)) {
                return e.desc;
            }
        }
        return null;
    }

    public static WayHandleTypeEnum get(Integer type) {

        for (WayHandleTypeEnum e : WayHandleTypeEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    public static WayHandleTypeEnum getByDesc(String desc) {

        for (WayHandleTypeEnum e : WayHandleTypeEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }


}
