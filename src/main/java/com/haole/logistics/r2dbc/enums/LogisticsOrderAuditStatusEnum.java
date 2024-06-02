package com.haole.logistics.r2dbc.enums;

public enum LogisticsOrderAuditStatusEnum {

    START(1, "待审核"),

    PASSED(2, "审核通过"),

    NOT_PASS(3, "审核不通过"),
    ;


    LogisticsOrderAuditStatusEnum(Integer type, String desc) {
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

        for (LogisticsOrderAuditStatusEnum e : LogisticsOrderAuditStatusEnum.values()) {
            if (e.getType().equals(type)) {
                return e.desc;
            }
        }
        return null;
    }

    public static LogisticsOrderAuditStatusEnum get(Integer type) {

        for (LogisticsOrderAuditStatusEnum e : LogisticsOrderAuditStatusEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    public static LogisticsOrderAuditStatusEnum getByDesc(String desc) {

        for (LogisticsOrderAuditStatusEnum e : LogisticsOrderAuditStatusEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }
}
