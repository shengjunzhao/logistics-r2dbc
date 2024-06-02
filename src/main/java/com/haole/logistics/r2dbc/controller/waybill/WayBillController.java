package com.haole.logistics.r2dbc.controller.waybill;

import com.haole.logistics.r2dbc.common.ActionResult;
import com.haole.logistics.r2dbc.common.PagedResultVO;
import com.haole.logistics.r2dbc.controller.param.waybill.DriverWayQueryParam;
import com.haole.logistics.r2dbc.controller.param.waybill.OpWayQueryParam;
import com.haole.logistics.r2dbc.controller.vo.WayBillVO;
import com.haole.logistics.r2dbc.dal.bo.waybill.DriverWayBillQueryParam;
import com.haole.logistics.r2dbc.dal.bo.waybill.OpWayBillQueryParam;
import com.haole.logistics.r2dbc.service.BeanMapper;
import com.haole.logistics.r2dbc.service.waybill.WayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * ClassName: WayBillController
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/5/31 20:50
 */
@RestController
@RequestMapping("/waybill")
public class WayBillController {

    private WayBillService wayBillService;

    private BeanMapper beanMapper;

    @PostMapping("/list/driver")
    public Mono<ActionResult<PagedResultVO<WayBillVO>>> queryWayBillOfDriver(@RequestBody @Validated DriverWayQueryParam param) {
        return wayBillService.queryWayBillOfDriver(beanMapper.map(param, DriverWayBillQueryParam.class))
                .flatMap(bo -> Mono.just(ActionResult.success(new PagedResultVO<>(beanMapper.mapAsList(bo.getRecords(), WayBillVO.class), bo.getPageNum(), bo.getPageSize(), bo.getTotal()))));

    }

    @PostMapping("/list/op")
    public Mono<ActionResult<PagedResultVO<WayBillVO>>> queryWayBillOfOp(@RequestBody @Validated OpWayQueryParam param) {
        return wayBillService.queryWayBillOfOp(beanMapper.map(param, OpWayBillQueryParam.class))
                .flatMap(bo -> Mono.just(ActionResult.success(new PagedResultVO<>(beanMapper.mapAsList(bo.getRecords(), WayBillVO.class), bo.getPageNum(), bo.getPageSize(), bo.getTotal()))));

    }


    @Autowired
    public void setWayBillService(WayBillService wayBillService) {
        this.wayBillService = wayBillService;
    }

    @Autowired
    public void setBeanMapper(BeanMapper beanMapper) {
        this.beanMapper = beanMapper;
    }
}
