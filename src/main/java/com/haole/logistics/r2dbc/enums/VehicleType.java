package com.haole.logistics.r2dbc.enums;

public enum VehicleType {

    TYPE_1("面包车"),
    TYPE_2("4.2米箱式货车"),
    TYPE_3("6.2米箱式/高栏车"),
    TYPE_4("7.6米箱式/高栏车"),
    TYPE_5("9.6米箱式/高栏"),
    TYPE_6("12.5米高栏"),
    TYPE_7("其他");


    VehicleType(String desc) {

        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
