package iti.jets.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

public class Factory {

    private static final String PERSISTENCE_UNIT_NAME = "ecommerce";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/shomya";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";
    private static final int MIN_IDLE = 5;
    private static final int MAX_POOL_SIZE = 20;

    private static EntityManagerFactory entityManagerFactory;
    private static HikariDataSource hikariDataSource;

    private Factory() {
        // Private constructor to prevent instantiation
    }

//    public static EntityManagerFactory getEntityMangerFactory(){
//        return entityManagerFactory;
//    }

    public static synchronized EntityManagerFactory getEntityMangerFactory() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    public static synchronized void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            try {
                entityManagerFactory.close();
                entityManagerFactory = null; // Ensure it is set to null after closing
            } catch (PersistenceException e) {
                e.printStackTrace(); // Consider using a logger here
            }
        }
    }

    public static synchronized HikariDataSource getHikariDataSource() {
        if (hikariDataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(JDBC_URL);
            config.setUsername(JDBC_USERNAME);
            config.setPassword(JDBC_PASSWORD);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            config.setMinimumIdle(MIN_IDLE);
            config.setMaximumPoolSize(MAX_POOL_SIZE);
            hikariDataSource = new HikariDataSource(config);
        }
        return hikariDataSource;
    }

    public static synchronized int getMaxPoolSize() {
        HikariDataSource dataSource = getHikariDataSource();
        return dataSource.getMaximumPoolSize();
    }

    public static synchronized int getMinPoolSize() {
        HikariDataSource dataSource = getHikariDataSource();
        return dataSource.getMinimumIdle();
    }

    public static synchronized void closeHikariDataSource() {
        if (hikariDataSource != null && !hikariDataSource.isClosed()) {
            hikariDataSource.close();
            hikariDataSource = null; // Ensure it is set to null after closing
        }
    }
}
