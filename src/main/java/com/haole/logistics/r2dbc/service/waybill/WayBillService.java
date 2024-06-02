package com.haole.logistics.r2dbc.service.waybill;

import com.haole.logistics.r2dbc.common.PagedResultVO;
import com.haole.logistics.r2dbc.dal.bo.waybill.DriverWayBillQueryParam;
import com.haole.logistics.r2dbc.dal.bo.waybill.OpWayBillQueryParam;
import com.haole.logistics.r2dbc.dal.bo.waybill.WayBillBO;
import reactor.core.publisher.Mono;

/**
 * ClassName: WayBillService
 *
 * @description:
 * @author: shengjunzhao
 * @since: 2024/5/30 12:42
 */
public interface WayBillService {

    Mono<PagedResultVO<WayBillBO>> queryWayBillOfDriver(DriverWayBillQueryParam param);

    Mono<PagedResultVO<WayBillBO>> queryWayBillOfOp(OpWayBillQueryParam param);
}
