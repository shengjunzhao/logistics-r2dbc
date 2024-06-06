package com.haole.logistics.r2dbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.haole.logistics.r2dbc"})
public class LogisticsR2dbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticsR2dbcApplication.class, args);
    }

}
