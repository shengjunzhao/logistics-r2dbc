package com.haole.logistics.r2dbc.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import java.time.Duration;

import static io.r2dbc.spi.ConnectionFactoryOptions.*;

//@Configuration
//@EnableR2dbcRepositories
//@EnableTransactionManagement
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        // 1. url 方式
//        return ConnectionFactories.get("r2dbc:mysql://logistics:sjzsjz@localhost:3306/logistics?zeroDate=use_round&serverZoneId=Asia/Shanghai");
        // 2. 编程方式
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(DRIVER, "mysql")
                .option(HOST, "localhost")
                .option(PORT, 3306)
                .option(USER, "logistics")
                .option(PASSWORD, "sjzsjz")
                .option(DATABASE, "logistics")
                .option(CONNECT_TIMEOUT, Duration.ofSeconds(3))

                .option(SSL, true)
                .option(Option.valueOf("zeroDate"), "use_null")
//                .option(Option.valueOf("sessionVariables"), new String[] { "sql_mode=ANSI_QUOTES", "time_zone=08:00" })
                .option(Option.valueOf("serverZoneId"), "Asia/Shanghai")
                .option(Option.valueOf("useServerPrepareStatement"), true)
                .option(Option.valueOf("serverZoneId"), "Asia/Shanghai")
                .option(Option.valueOf("compressionAlgorithms"), "zstd")
                .option(Option.valueOf("autodetectExtensions"), false)
//                .option(Option.valueOf("passwordPublisher"), Mono.just("password"))
                .option(Option.valueOf("allowPublicKeyRetrieval"), true)
                .build();
        return ConnectionFactories.get(options);
        // 3. Mysql编程配置
//        MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
//                .host("127.0.0.1")
//                .user("root")
//                .port(3306)
//                .password("sjzsjz")
//                .database("logistics")
//                .createDatabaseIfNotExist(false)
//                .connectTimeout(Duration.ofSeconds(3))
//                .build();
//        return MySqlConnectionFactory.from(configuration);

    }

    @Bean
    public ReactiveTransactionManager transactionManager() {
        return new R2dbcTransactionManager(this.connectionFactory());
    }
}
