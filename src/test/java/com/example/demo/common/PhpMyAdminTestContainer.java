package com.example.demo.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhpMyAdminTestContainer {
    public static MySQLContainer container;

    static {
        container = new MySQLContainer()
                .withUsername("root")
                .withPassword("root")
                .withDatabaseName("test");

        container.start();
    }
}
