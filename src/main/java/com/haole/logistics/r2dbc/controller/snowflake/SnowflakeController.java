package com.haole.logistics.r2dbc.controller.snowflake;

import com.haole.logistics.r2dbc.service.snowflake.SnowflakeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/snowflake")
public class SnowflakeController {

    private static final Logger logger = LoggerFactory.getLogger(SnowflakeController.class);


    private SnowflakeGenerator generator;

    private static final String WAYBILLOFDRIVER = """
            select
                    w.way_bill_id wayBillId,
                    w.way_bill_sn,
                    w.logistics_bill_no logisticsBillNo,
                    w.way_bill_status wayBillStatus,
                    w.trans_type transType,
                    w.way_handle_type wayHandleType,
                    w.logistics_enterprise_name as logisticsEnterpriseName,
                    w.logistics_enterprise_code logisticsEnterpriseCode,
                    w.license_plate_color licensePlateColor,
                    w.vcln vcln,
                    w.vehicle_type vehicleType,
                    w.driver_name driverName,
                    w.driver_tel driverTel,
                    w.driver_license_type driverLicenseType,
                    w.driver_license_number driverLicenseNumber,
                    w.vehicle_license_number vehicleLicenseNumber,
                    w.freight freight,
                    w.cargo_type cargoType,
                    w.cargo_count cargoCount,
                    w.cargo_weight cargoWeight,
                    w.cargo_volume cargoVolume,
                    w.cargo_count_unit cargoCountUnit,
                    w.cargo_weight_unit cargoWeightUnit,
                    w.cargo_volume_unit cargoVolumeUnit,
                    w.deliver_time deliverTime,
                    w.receive_time receiveTime,
                    w.create_time createTime,
                    o.source_system sourceSystem,
                    o.source_bill_sn sourceBillSn,
                    o.parent_source_bill_no parentSourceBillNo,
                    o.source_bill_type sourceBillType,
                    o.source_bill_create_time sourceBillCreateTime,
                    o.min_estimate_arrival_time minEstimateArrivalTime,
                    o.max_estimate_arrival_time maxEstimateArrivalTime,
                    da.enterprise_code deliverEnterpriseCode,
                    da.enterprise_name deliverEnterpriseName,
                    da.contract_name deliverPersonName,
                    da.contact_tel deliverPersonTel,
                    da.province_code deliverProvinceCode,
                    da.province_name deliverProvinceName,
                    da.city_code deliverCityCode,
                    da.city_name deliverCityName,
                    da.county_code deliverCountyCode,
                    da.county_name deliverCountyName,
                    da.town_code deliverTownCode,
                    da.town_name deliverTownName,
                    da.detail_address deliverDetailAddress,
                    ra.enterprise_code receiverEnterpriseCode,
                    ra.enterprise_name receiverEnterpriseName,
                    ra.contract_name receiverPersonName,
                    ra.contact_tel receiverPersonTel,
                    ra.province_code receiverProvinceCode,
                    ra.province_name receiverProvinceName,
                    ra.city_code receiverCityCode,
                    ra.city_name receiverCityName,
                    ra.county_code receiverCountyCode,
                    ra.county_name receiverCountyName,
                    ra.town_code receiverTownCode,
                    ra.town_name receiverTownName,
                    ra.detail_address receiverDetailAddress
            from way_bill w
                    join logistics_order_way r on w.way_bill_id = r.way_bill_id
                    join logistics_order_bill o on r.logistics_order_id = o.logistics_order_id
                    join way_bill_address da on w.way_bill_id = da.way_bill_id and da.logistics_address_type = 1
                    join way_bill_address ra on w.way_bill_id = ra.way_bill_id and ra.logistics_address_type = 2
                    where w.delete_flag =0 and r.delete_flag =0 and o.delete_flag =0
                    and da.delete_flag =0 and ra.delete_flag =0
                    and w.way_handle_type = 2
            """;


    @GetMapping("/id/next")
    public Mono<Long> nexId() {
        long sequenceMask = (2 << (21 -1))- 1L;
        logger.info("sequenceMask={}", sequenceMask);
        long id1 = generator.nextId();
        long id2 = generator.nextId();
        Flux.just(1, 2).concatWith(Mono.error(new IllegalStateException())).onErrorReturn(0).subscribe(System.out::println);

        System.out.println(WAYBILLOFDRIVER);
        return Mono.just(id1 + id2);
    }


    @Autowired
    public void setGenerator(SnowflakeGenerator generator) {
        this.generator = generator;
    }
}
