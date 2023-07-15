package com.octskyout.blog;

import com.octskyout.config.R2dbcPostgreContainer;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.r2dbc.core.DatabaseClient;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

@Testcontainers
public class PostgreContainerSetting {
    Logger log = LoggerFactory.getLogger(PostgreContainerSetting.class);

    @Container
    private R2dbcPostgreContainer postgresqlContainer = new R2dbcPostgreContainer("postgres:15-bullseye")
        .withUsername("root")
        .withPassword("password")
        .withDatabaseName("blog")
        .setUrlParams(List.of("schema=blog_schema"));

    @Test
    void test_connection() {
        var connectionUrl = (postgresqlContainer.getR2dbcUrl())
            .replace("jdbc", "r2dbc");
        log.info("r2dbc url is {}", connectionUrl);

        ConnectionFactory connectionFactory =
            ConnectionFactories.get(connectionUrl);

        DefaultReactiveDataAccessStrategy
            strategy = new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE);
        DatabaseClient databaseClient = DatabaseClient.builder()
            .connectionFactory(connectionFactory)
            .bindMarkers(PostgresDialect.INSTANCE.getBindMarkersFactory())
            .build();
        var r2dbcTemplate = new R2dbcEntityTemplate(databaseClient, strategy);

        r2dbcTemplate.getDatabaseClient().sql("SELECT 1;")
            .fetch()
            .first()
            .log()
            .as(StepVerifier::create)
            .expectNextCount(1L)
            .verifyComplete();
    }
}
