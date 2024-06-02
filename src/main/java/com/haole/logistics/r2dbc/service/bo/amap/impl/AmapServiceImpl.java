package com.haole.logistics.r2dbc.service.bo.amap.impl;

import com.haole.logistics.r2dbc.service.amap.AmapService;
import com.haole.logistics.r2dbc.service.bo.amap.AmapReverseAddressBO;
import com.haole.logistics.r2dbc.service.dto.amap.AmapReverseAddressDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmapServiceImpl implements AmapService {
    @Override
    public List<AmapReverseAddressDTO> queryReverseAddressByZT(List<AmapReverseAddressBO> addresses) {
        List<AmapReverseAddressDTO> dtoList = new ArrayList<>();
        for (AmapReverseAddressBO address : addresses) {
            AmapReverseAddressDTO dto = new AmapReverseAddressDTO();
            dto.setLonLat("116.480881,39.989410");
            dtoList.add(dto);
        }
        return dtoList;
    }
}
