package com.training.app;

import com.shiyatsu.lib.db.handler.DbConnector;
import com.shiyatsu.logger.ILoggerService;
import com.shiyatsu.logger.impl.LoggerService;
import com.training.app.auth.CustomPasswordEncoder;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class Context {

    private static DbConnector dbConnector = new DbConnector();
    private static final ILoggerService logger = LoggerService.getLoggingService();
    private static final Map<UUID, User> users = new HashMap<>();

    public Context(DatabaseConfig databaseConfig) {
        dbConnector.setHost(databaseConfig.getHost());
        dbConnector.setPort(databaseConfig.getPort());
        dbConnector.setDb(databaseConfig.getDatabase());
        dbConnector.setUser(databaseConfig.getUser());
        dbConnector.setPassword(databaseConfig.getPassword());
    }

    @PostConstruct
    public void init() {
        initUsers();
    }

    private static void initUsers() {
        UUID adminId = UUID.randomUUID();
        UUID totoId = UUID.randomUUID();
        users.putIfAbsent(adminId, new User(adminId, "admin", CustomPasswordEncoder.encode("admin"),"info@gmail.com"));
        users.get(adminId).getRoles().add("ROLE_ADMIN");
        users.putIfAbsent(totoId, new User(totoId, "toto", CustomPasswordEncoder.encode("toto"),"info@gmail.com"));
        users.get(totoId).getRoles().add("ROLE_USER");
        logger.info(Context.class, "Admin users loaded");
    }

    public static DbConnector getDbConnector(DatabaseConfig databaseConfig) {
        if (dbConnector == null) {
            dbConnector = new DbConnector();
            dbConnector.setHost(databaseConfig.getHost());
            dbConnector.setPort(databaseConfig.getPort());
            dbConnector.setDb(databaseConfig.getDatabase());
            dbConnector.setUser(databaseConfig.getUser());
            dbConnector.setPassword(databaseConfig.getPassword());
        }
        return dbConnector;
    }

    public static void initDbConnector(DbConnector dbCon) {
        dbConnector.setHost(dbCon.getHost());
        dbConnector.setPort(dbCon.getPort());
        dbConnector.setDb(dbCon.getDb());
        dbConnector.setUser(dbCon.getUser());
        dbConnector.setPassword(dbCon.getPassword());
        logger.info(Context.class, "Connector DB initialized");
    }

    public static List<User> getUsersAsList() {
        return new ArrayList<>(users.values());
    }

    public static Map<UUID, User> getUsers() {
        return users;
    }

}
