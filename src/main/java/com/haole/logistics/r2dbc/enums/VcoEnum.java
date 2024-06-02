package com.haole.logistics.r2dbc.enums;

public enum VcoEnum {

    BLUE("蓝牌", 1),//蓝牌
    YELLO("黄牌", 2),//黄牌
    YELLO_BLUE("黄绿色", 3);// 黄绿色


    VcoEnum(String desc, int type) {
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

        for (VcoEnum e : VcoEnum.values()) {
            if (e.getType().equals(type)) {
                return e.desc;
            }
        }
        return null;
    }

    public static VcoEnum get(int type) {

        for (VcoEnum e : VcoEnum.values()) {
            if (e.getType() == type) {
                return e;
            }
        }
        return null;
    }

    public static VcoEnum getByEnum(String desc) {

        for (VcoEnum e : VcoEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }
}
