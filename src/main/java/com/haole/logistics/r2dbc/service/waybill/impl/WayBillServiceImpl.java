package com.haole.logistics.r2dbc.service.waybill.impl;

import com.haole.logistics.r2dbc.common.PagedResultVO;
import com.haole.logistics.r2dbc.dal.bo.waybill.DriverWayBillQueryParam;
import com.haole.logistics.r2dbc.dal.bo.waybill.OpWayBillQueryParam;
import com.haole.logistics.r2dbc.dal.bo.waybill.WayBillBO;
import com.haole.logistics.r2dbc.service.PageQueryService;
import com.haole.logistics.r2dbc.service.waybill.WayBillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * ClassName: WayBillServiceImpl
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/5/30 12:43
 */
@Service
public class WayBillServiceImpl implements WayBillService {

    private static final Logger logger = LoggerFactory.getLogger(WayBillServiceImpl.class);

    private static final String WAYBILL_DRIVER = """
            select
                    w.way_bill_id ,
                    w.way_bill_sn,
                    w.logistics_bill_no ,
                    w.way_bill_status ,
                    w.trans_type ,
                    w.way_handle_type ,
                    w.logistics_enterprise_name ,
                    w.logistics_enterprise_code ,
                    w.license_plate_color ,
                    w.vcln vcln,
                    w.vehicle_type ,
                    w.driver_name ,
                    w.driver_tel ,
                    w.driver_license_type ,
                    w.driver_license_number ,
                    w.vehicle_license_number ,
                    w.freight freight,
                    w.cargo_type ,
                    w.cargo_count ,
                    w.cargo_weight ,
                    w.cargo_volume ,
                    w.cargo_count_unit ,
                    w.cargo_weight_unit ,
                    w.cargo_volume_unit ,
                    w.deliver_time ,
                    w.receive_time ,
                    w.create_time ,
                    o.source_system ,
                    o.source_bill_sn ,
                    o.parent_source_bill_no ,
                    o.source_bill_type ,
                    o.source_bill_create_time ,
                    o.min_estimate_arrival_time ,
                    o.max_estimate_arrival_time ,
                    da.enterprise_code deliver_enterprise_code,
                    da.enterprise_name deliver_enterprise_name,
                    da.contract_name deliver_person_name,
                    da.contact_tel deliver_person_tel,
                    da.province_code deliver_province_code,
                    da.province_name deliver_province_name,
                    da.city_code deliver_city_code,
                    da.city_name deliver_city_name,
                    da.county_code deliver_county_code,
                    da.county_name deliver_county_name,
                    da.town_code deliver_town_code,
                    da.town_name deliver_town_name,
                    da.detail_address deliver_detail_address,
                    ra.enterprise_code receiver_enterprise_code,
                    ra.enterprise_name receiver_enterprise_name,
                    ra.contract_name receiver_person_name,
                    ra.contact_tel receiver_person_tel,
                    ra.province_code receiver_province_cde,
                    ra.province_name receiver_province_name,
                    ra.city_code receiver_city_code,
                    ra.city_name receiver_city_name,
                    ra.county_code receiver_county_code,
                    ra.county_name receiver_county_name,
                    ra.town_code receiver_town_code,
                    ra.town_name receiver_town_name,
                    ra.detail_address receiver_detail_address
            from way_bill w
                    join logistics_order_way r on w.way_bill_id = r.way_bill_id
                    join logistics_order_bill o on r.logistics_order_id = o.logistics_order_id
                    join way_bill_address da on w.way_bill_id = da.way_bill_id and da.logistics_address_type = 1
                    join way_bill_address ra on w.way_bill_id = ra.way_bill_id and ra.logistics_address_type = 2
                    where w.delete_flag =0 and r.delete_flag =0 and o.delete_flag =0
                    and da.delete_flag =0 and ra.delete_flag =0
                    and w.way_handle_type = 2
            """;

    private static final String WAYBILL_OP = """
            select
                    w.way_bill_id ,
                    w.way_bill_sn,
                    w.logistics_bill_no ,
                    w.way_bill_status ,
                    w.trans_type ,
                    w.way_handle_type ,
                    w.logistics_enterprise_name ,
                    w.logistics_enterprise_code ,
                    w.license_plate_color ,
                    w.vcln vcln,
                    w.vehicle_type ,
                    w.driver_name ,
                    w.driver_tel ,
                    w.driver_license_type ,
                    w.driver_license_number ,
                    w.vehicle_license_number ,
                    w.freight freight,
                    w.cargo_type ,
                    w.cargo_count ,
                    w.cargo_weight ,
                    w.cargo_volume ,
                    w.cargo_count_unit ,
                    w.cargo_weight_unit ,
                    w.cargo_volume_unit ,
                    w.deliver_time ,
                    w.receive_time ,
                    w.create_time ,
                    o.source_system ,
                    o.source_bill_sn ,
                    o.parent_source_bill_no ,
                    o.source_bill_type ,
                    o.source_bill_create_time ,
                    o.min_estimate_arrival_time ,
                    o.max_estimate_arrival_time ,
                    da.enterprise_code deliver_enterprise_code,
                    da.enterprise_name deliver_enterprise_name,
                    da.contract_name deliver_person_name,
                    da.contact_tel deliver_person_tel,
                    da.province_code deliver_province_code,
                    da.province_name deliver_province_name,
                    da.city_code deliver_city_code,
                    da.city_name deliver_city_name,
                    da.county_code deliver_county_code,
                    da.county_name deliver_county_name,
                    da.town_code deliver_town_code,
                    da.town_name deliver_town_name,
                    da.detail_address deliver_detail_address,
                    ra.enterprise_code receiver_enterprise_code,
                    ra.enterprise_name receiver_enterprise_name,
                    ra.contract_name receiver_person_name,
                    ra.contact_tel receiver_person_tel,
                    ra.province_code receiver_province_cde,
                    ra.province_name receiver_province_name,
                    ra.city_code receiver_city_code,
                    ra.city_name receiver_city_name,
                    ra.county_code receiver_county_code,
                    ra.county_name receiver_county_name,
                    ra.town_code receiver_town_code,
                    ra.town_name receiver_town_name,
                    ra.detail_address receiver_detail_address,
                    w.trace_flag,
                    w.trace_first_time
            from way_bill w
                    join logistics_order_way r on w.way_bill_id = r.way_bill_id
                    join logistics_order_bill o on r.logistics_order_id = o.logistics_order_id
                    join way_bill_address da on w.way_bill_id = da.way_bill_id and da.logistics_address_type = 1
                    join way_bill_address ra on w.way_bill_id = ra.way_bill_id and ra.logistics_address_type = 2
                    where w.delete_flag =0 and r.delete_flag =0 and o.delete_flag =0
                    and da.delete_flag =0 and ra.delete_flag =0
            """;


    private R2dbcEntityTemplate template;

    private PageQueryService pageQueryService;


    @Override
    public Mono<PagedResultVO<WayBillBO>> queryWayBillOfDriver(DriverWayBillQueryParam param) {
        StringBuilder sb = new StringBuilder(WAYBILL_DRIVER).append(" ");
        if (StringUtils.hasText(param.getMobileNo())) {
            sb.append("and w.driver_tel='").append(param.getMobileNo()).append("' ");
        }
        if (StringUtils.hasText(param.getWayBillSn())) {
            sb.append("  and w.way_bill_sn like concat('%',").append(param.getWayBillSn()).append("'%'").append(" ");
        }
        if (null != param.getWayBillStatus()) {
            sb.append("and w.way_bill_status").append(
                    switch (param.getWayBillStatus()) {
                        case 1 -> " =1";
                        case 2 -> " in (10,20)";
                        case 3 -> " in (30,31)";
                        case 4 -> " = 40";
                        default -> "= w.way_bill_status";
                    }
            ).append(" ");
        }
        String sql = sb.toString();
        // DriverWayBillQueryParam::getPageNum从1开始，而Pageable从0开始
        Pageable page = PageRequest.of(param.getPageNum() - 1, param.getPageSize());
        return pageQueryService.pageQuery(sb.toString(), page, WayBillBO.class);
    }


    @Override
    public Mono<PagedResultVO<WayBillBO>> queryWayBillOfOp(OpWayBillQueryParam param) {
        StringBuilder sb = new StringBuilder(WAYBILL_OP).append(" ");
        if (null != param.getWayBillId()) {
            sb.append("and w.way_bill_id ='").append(param.getWayBillId()).append("' ");
        }
        if (StringUtils.hasText(param.getWayBillSn())) {
            sb.append("and w.way_bill_sn ='").append(param.getWayBillSn()).append("' ");
        }
        if (StringUtils.hasText(param.getLogisticsBillNo())) {
            sb.append("and w.logistics_bill_no like concat('%',").append(param.getLogisticsBillNo()).append(",'%')").append(" ");
        }
        if (null != param.getTransType()) {
            sb.append("and w.trans_type =").append(param.getTransType()).append(" ");
        }
        if (null != param.getWayBillStatus()) {
            sb.append("and w.way_bill_status").append(
                    switch (param.getWayBillStatus()) {
                        case 1 -> " = 1";
                        case 2 -> " = 10";
                        case 3 -> " = 3";
                        case 4 -> " in (20,30,31)";
                        case 5 -> " = 40";
                        case 6 -> " = 50";
                        default -> " = w.way_bill_status";
                    }
            ).append(" ");
        }
        if (StringUtils.hasText(param.getSourceBillSn())) {
            sb.append("and o.source_Bill_Sn ='").append(param.getSourceBillSn()).append("' ");
        }
        if (null != param.getSourceBillType()) {
            sb.append(" and o.source_Bill_type =").append(param.getSourceBillType()).append(" ");
        }
        if (StringUtils.hasText(param.getCarrier())) {
            sb.append("and (w.logistics_enterprise_name like concat('%',").append(param.getCarrier())
                    .append(",'%') or w.driver_name like concat('%',")
                    .append(param.getCarrier()).append(",'%'))").append(" ");
        }
        if (StringUtils.hasText(param.getDeliverCountyCode())) {
            sb.append("and da.county_code ='").append(param.getDeliverCountyCode()).append("' ");
        }
        if (StringUtils.hasText(param.getReceiverCountyCode())) {
            sb.append("and ra.county_code ='").append(param.getReceiverCountyCode()).append("' ");
        }
        if (StringUtils.hasText(param.getCreateTimeStart())) {
            sb.append("and w.create_time >= concat('").append(param.getCreateTimeStart()).append(",' 00:00:00')").append(" ");
        }
        if (StringUtils.hasText(param.getCreateTimeEnd())) {
            sb.append("and w.create_time <= concat('").append(param.getCreateTimeStart()).append(",' 23:59:59')").append(" ");
        }
        if (StringUtils.hasText(param.getSourceSystem())) {
            sb.append("and o.source_system ='").append(param.getSourceSystem()).append("' ");
        }
        if (StringUtils.hasText(param.getDeliverEnterpriseCode())) {
            sb.append("and da.enterprise_code ='").append(param.getDeliverEnterpriseCode()).append("' ");
        }
        if (StringUtils.hasText(param.getReceiverEnterpriseCode())) {
            sb.append("and ra.enterprise_code ='").append(param.getReceiverEnterpriseCode()).append("' ");
        }
        if (StringUtils.hasText(param.getDeliverEnterpriseName())) {
            sb.append("and da.enterprise_name like concat('%','").append(param.getDeliverEnterpriseName())
                    .append(",'%'))").append(" ");
        }
        if (StringUtils.hasText(param.getReceiverEnterpriseName())) {
            sb.append("and ra.enterprise_name like concat('%','").append(param.getReceiverEnterpriseName())
                    .append(",'%'))").append(" ");
        }
        if (null != param.getTraceFlag()) {
            sb.append(" and w.trace_Flag =").append(param.getTraceFlag()).append(" ");
        }
        Pageable page = PageRequest.of(param.getPageNum() - 1, param.getPageSize());
        return pageQueryService.pageQuery(sb.toString(), page, WayBillBO.class);
    }

    @Autowired
    public void setTemplate(R2dbcEntityTemplate template) {
        this.template = template;
    }

    @Autowired
    public void setPageQueryService(PageQueryService pageQueryService) {
        this.pageQueryService = pageQueryService;
    }
}
