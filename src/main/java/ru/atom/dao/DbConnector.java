package ru.atom.dao;

/**
 * Created by sergey on 3/25/17.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


class DbConnector {
    private static final Logger log = LoggerFactory.getLogger(DbConnector.class);

    private static final String URL_TEMPLATE = "postgresql://%s:%d/%s";
    private static final String URL ="jdbc:postgresql://ec2-54-195-252-243.eu-west-1.compute.amazonaws.com:5432/dbq1gtnst1km6m";
    private static final String HOST = "ec2-54-195-252-243.eu-west-1.compute.amazonaws.com";
    private static final int PORT = 5432;
    private static final String DB_NAME = "dbq1gtnst1km6m";
    private static final String USER = "ehvuvonbuftrxc";
    private static final String PASSWORD = "f6b5a4c4530f1dc13313468672aa1fdea3bd9c063a41d89d38d9f4b428d97515";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Failed to load jdbc driver.", e);
            System.exit(-1);
        }


        log.info("Success. DbConnector init.");
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private DbConnector() { }
}