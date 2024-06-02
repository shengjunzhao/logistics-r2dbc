package com.haole.logistics.r2dbc.enums;

public enum LogisticsAddressTypeEnum {

    START_POINT(1, "起点"),
    END_POINT(2, "终点"),

    ;

    LogisticsAddressTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Integer type;
    private String desc;

    public Integer getType() {
        return type;
    }

    public static String getByType(Integer type) {
        if (type == null) {
            return null;
        }
        for (LogisticsAddressTypeEnum e : LogisticsAddressTypeEnum.values()) {
            if (e.getType().equals(type)) {
                return e.desc;
            }
        }
        return null;
    }

    public static LogisticsAddressTypeEnum get(Integer type) {
        if (type == null) {
            return null;
        }
        for (LogisticsAddressTypeEnum e : LogisticsAddressTypeEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

}
