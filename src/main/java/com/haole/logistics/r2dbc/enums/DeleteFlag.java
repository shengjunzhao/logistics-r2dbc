package com.haole.logistics.r2dbc.enums;

public enum DeleteFlag {

    NOT_DELETED(0, "未删除"),
    DELETED(1, "已删除");


    DeleteFlag(Integer type, String desc) {
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
        for (DeleteFlag e : DeleteFlag.values()) {
            if (e.getType().intValue() == type.intValue()) {
                return e.desc;
            }
        }
        return null;
    }

    public static DeleteFlag get(Integer type) {
        if (type == null) {
            return null;
        }
        for (DeleteFlag e : DeleteFlag.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

}
