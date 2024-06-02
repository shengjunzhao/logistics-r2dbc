package com.haole.logistics.r2dbc.service.bo.amap;

import java.io.Serial;
import java.io.Serializable;

public class AmapReverseAddressBO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1276146676359342331L;

    /**
     * 详细地址
     */
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
