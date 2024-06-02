package com.haole.logistics.r2dbc.enums;

public enum DriverLicenseType {

    A1("A1"),
    A2("A2"),
    B1("B1"),
    B2("B2"),
    C1("C1"),
    ;


    DriverLicenseType(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }
}
