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

    @GetMapping("/id/next")
    public Mono<Long> nexId() {
        long sequenceMask = (2 << (21 -1))- 1L;
        logger.info("sequenceMask={}", sequenceMask);
        long id1 = generator.nextId();
        long id2 = generator.nextId();
        Flux.just(1, 2).concatWith(Mono.error(new IllegalStateException())).onErrorReturn(0).subscribe(System.out::println);

        return Mono.just(id1 + id2);
    }


    @Autowired
    public void setGenerator(SnowflakeGenerator generator) {
        this.generator = generator;
    }
}
