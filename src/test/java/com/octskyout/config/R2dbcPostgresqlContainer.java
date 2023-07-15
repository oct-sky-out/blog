package com.octskyout.config;

import java.util.List;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

public class R2dbcPostgresqlContainer<SELF extends R2dbcPostgresqlContainer<SELF>> extends PostgreSQLContainer<SELF> {
    private List<String> urlParams;

    public R2dbcPostgresqlContainer(String dockerImageName) {
        super(dockerImageName);
        super.withCopyFileToContainer(
            MountableFile.forClasspathResource("schema.sql"), "/docker-entrypoint-initdb.d/init.sql");
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
