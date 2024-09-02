package com.pedro.jfsql.modules.database;


import com.pedro.jfsql.handler.ConnectionHandler;
import com.pedro.jfsql.model.enumeration.DatabaseConnMode;
import com.pedro.jfsql.model.enumeration.DatabaseType;
import org.hibernate.service.spi.ServiceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public DatabaseConnection(ConnectionHandler connectionHandler) {
    }

    public static Connection initializeConnection(String name, String host, String port, String database, String username, String password, DatabaseType databaseType, DatabaseConnMode connMode) throws SQLException {
        String driver = getDatabaseType(databaseType);
        String url = getUrl(databaseType, host, port, database, connMode);
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServiceException("Error initializing connection", e);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ServiceException("Error closing connection", e);
        }
    }

    private static String getUrl(DatabaseType databaseType, String host, String port, String database, DatabaseConnMode connMode) {
        if (databaseType == DatabaseType.POSTGRES) {
            return "jdbc:postgresql://" + host + ":" + port + "/" + database;
        } else if (databaseType == DatabaseType.MYSQL) {
            return "jdbc:mysql://" + host + ":" + port + "/" + database;
        } else if (databaseType == DatabaseType.ORACLE) {
            return (connMode == DatabaseConnMode.SID) ? "jdbc:oracle:thin:@" + host + ":" + port + ":" + database : "jdbc:oracle:thin:@" + host + ":" + port + "/" + database;
        } else {
            throw new ServiceException("Database type not supported");
        }
    }

    private static String getDatabaseType(DatabaseType databaseType) {
        if (databaseType == DatabaseType.POSTGRES) {
            return "org.postgresql.Driver";
        } else if (databaseType == DatabaseType.MYSQL) {
            return "com.mysql.cj.jdbc.Driver";
        } else if (databaseType == DatabaseType.ORACLE) {
            return "oracle.jdbc.driver.OracleDriver";
        } else {
            throw new ServiceException("Database type not supported");
        }
    }

}
