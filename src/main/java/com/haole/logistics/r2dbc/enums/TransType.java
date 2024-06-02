package com.haole.logistics.r2dbc.enums;

public enum TransType {

    EXPRESS(1, "快递运输", "EXPRESS"),//快递运输
    LTL(2, "零担运输", "LTL"),//零担运输
    FT(3, "整车运输", "FT");// 整车运输


    TransType(Integer type, String desc, String code) {
        this.type = type;
        this.desc = desc;
        this.code = code;
    }

    private Integer type;
    private String desc;
    private String code;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }

    public static String getByType(Integer type) {

        for (TransType e : TransType.values()) {
            if (e.getType().equals(type)) {
                return e.desc;
            }
        }
        return null;
    }

    public static TransType get(Integer type) {

        for (TransType e : TransType.values()) {
            if (e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }

    public static TransType getByDesc(String desc) {

        for (TransType e : TransType.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return null;
    }

    public static TransType getByCode(String code) {

        for (TransType e : TransType.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

}
