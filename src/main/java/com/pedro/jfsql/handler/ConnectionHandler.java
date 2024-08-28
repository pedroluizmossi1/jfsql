package com.pedro.jfsql.handler;

import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.model.enumeration.DatabaseType;
import com.pedro.jfsql.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConnectionHandler {

    private final ConnectionService connectionService;

    @Autowired
    public ConnectionHandler(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    public void createConnectionHandler(Connection connection) {
        connectionService.createConnection(connection);
    }

    public List<Connection> findAllConnections() {
        return connectionService.findAllConnections();
    }

    public void deleteConnection(Long id) {
        connectionService.deleteConnection(id);
    }

    public DatabaseType[] getDatabaseTypes() {
        return DatabaseType.values();
    }

    public Connection findConnectionById(Long id) {
        return connectionService.findConnectionById(id);
    }

    public boolean testConnection(Long id) {
        return connectionService.testConnection(id);
    }
}