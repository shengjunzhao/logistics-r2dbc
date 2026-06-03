package com.haole.logistics.r2dbc.enums;

import com.haole.logistics.r2dbc.util.EnumCache;

public enum LogisticsWayGenerateStatusEnum {

    NO(0, "未生成运单"),
    YES(1, "已生成运单"),
    ;

    static {
        EnumCache.registerByName(LogisticsWayGenerateStatusEnum.class, values());
        EnumCache.registerByValue(LogisticsWayGenerateStatusEnum.class, values(), LogisticsWayGenerateStatusEnum::getType);
    }
//   EnumCache.findByValue(LogisticsWayGenerateStatusEnum.class,0,null);


    LogisticsWayGenerateStatusEnum(Integer type, String desc) {
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

        for (LogisticsWayGenerateStatusEnum e : LogisticsWayGenerateStatusEnum.values()) {
            if (e.getType().equals(type)) {
                return e.desc;
            }
        }
        return null;
    }

    public static LogisticsWayGenerateStatusEnum get(Integer type) {

        for (LogisticsWayGenerateStatusEnum e : LogisticsWayGenerateStatusEnum.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    public static LogisticsWayGenerateStatusEnum getByDesc(String desc) {

        for (LogisticsWayGenerateStatusEnum e : LogisticsWayGenerateStatusEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }
}
