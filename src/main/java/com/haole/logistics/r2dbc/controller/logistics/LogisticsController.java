package com.haole.logistics.r2dbc.controller.logistics;


import com.haole.logistics.r2dbc.common.ActionResult;
import com.haole.logistics.r2dbc.controller.param.logistics.LogisticsOrderCreateParam;
import com.haole.logistics.r2dbc.dal.bo.logistics.LogisticsOrderCreateBO;
import com.haole.logistics.r2dbc.service.BeanMapper;
import com.haole.logistics.r2dbc.service.logistics.LogisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    private static final Logger logger = LoggerFactory.getLogger(LogisticsController.class);

    private BeanMapper beanMapper;

    private LogisticsService logisticsService;


    @PostMapping("/create")
    public Mono<ActionResult<Long>> createLogistics(@RequestBody @Validated Mono<LogisticsOrderCreateParam> param) {
        return logisticsService.createLogistics(param.flatMap(c -> Mono.just(beanMapper.map(c, LogisticsOrderCreateBO.class))));
    }

    @Autowired
    public void setBeanMapper(BeanMapper beanMapper) {
        this.beanMapper = beanMapper;
    }

    @Autowired
    public void setLogisticsService(LogisticsService logisticsService) {
        this.logisticsService = logisticsService;
    }
}
