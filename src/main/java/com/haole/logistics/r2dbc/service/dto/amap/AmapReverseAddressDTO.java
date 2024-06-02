package com.haole.logistics.r2dbc.service.dto.amap;

import java.io.Serial;
import java.io.Serializable;

public class AmapReverseAddressDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -7125609999693681551L;

    /**
     * 经度在前，纬度在后  ,分割
     */
    private String lonLat;

    public String getLonLat() {
        return lonLat;
    }

    public void setLonLat(String lonLat) {
        this.lonLat = lonLat;
    }
}
