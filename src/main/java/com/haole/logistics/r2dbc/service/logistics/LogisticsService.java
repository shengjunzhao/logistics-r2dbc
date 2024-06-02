package com.haole.logistics.r2dbc.service.logistics;

import com.haole.logistics.r2dbc.common.ActionResult;
import com.haole.logistics.r2dbc.dal.bo.logistics.LogisticsOrderCreateBO;
import reactor.core.publisher.Mono;

public interface LogisticsService {


    Mono<ActionResult<Long>> createLogistics(Mono<LogisticsOrderCreateBO> param);


}
