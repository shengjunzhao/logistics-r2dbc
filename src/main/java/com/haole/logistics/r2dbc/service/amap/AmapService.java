package com.haole.logistics.r2dbc.service.amap;

import com.haole.logistics.r2dbc.service.bo.amap.AmapReverseAddressBO;
import com.haole.logistics.r2dbc.service.dto.amap.AmapReverseAddressDTO;

import java.util.List;

public interface AmapService {

    /**
     * 调用高德地址转换
     * @param addresses
     * @return
     */
    List<AmapReverseAddressDTO> queryReverseAddressByZT(List<AmapReverseAddressBO> addresses);

}
