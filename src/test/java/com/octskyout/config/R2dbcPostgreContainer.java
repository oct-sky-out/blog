package com.octskyout.config;

import java.util.List;
import org.testcontainers.containers.PostgreSQLContainer;

public class R2dbcPostgreContainer<SELF extends R2dbcPostgreContainer<SELF>> extends PostgreSQLContainer<SELF> {
    private List<String> urlParams;

    public R2dbcPostgreContainer(String dockerImageName) {
        super(dockerImageName);
    }

    public String getR2dbcUrl() {
        return "r2dbc:postgresql://{username}:{password}@{host}:{port}/{databaseName}?{urlParams}"
            .replace("{username}", super.getUsername())
            .replace("{password}", super.getPassword())
            .replace("{host}", super.getHost())
            .replace("{port}", super.getMappedPort(POSTGRESQL_PORT).toString())
            .replace("{databaseName}", super.getDatabaseName())
            .replace("{urlParams}", String.join("&", urlParams));
    }

    public SELF setUrlParams(List<String> urlParams) {
        this.urlParams = urlParams;
        return self();
    }

    @Override
    public SELF withDatabaseName(String databaseName) {
        super.withDatabaseName(databaseName);
        return self();
    }

    @Override
    public SELF withUsername(String username) {
        super.withUsername(username);
        return self();
    }

    @Override
    public SELF withPassword(String password) {
        super.withPassword(password);
        return self();
    }
}
