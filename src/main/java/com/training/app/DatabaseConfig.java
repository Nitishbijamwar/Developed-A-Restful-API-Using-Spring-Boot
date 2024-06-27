package com.training.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConfig {

    @Value("${db.host}")
    private String host;

    @Value("${db.port}")
    private int port;

    @Value("${db.database}")
    private String database;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
