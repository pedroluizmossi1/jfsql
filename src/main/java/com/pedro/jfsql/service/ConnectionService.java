package com.pedro.jfsql.service;

import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.modules.database.DatabaseConnection;
import com.pedro.jfsql.repository.ConnectionRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ConnectionService {

    private final Logger LOGGER = Logger.getLogger(ConnectionService.class.getName());
    private final ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public List<Connection> findAllConnections() {
        return connectionRepository.findAll();
    }

    public Connection createConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public void deleteConnection(Long id) {
    }

    public Connection findConnectionById(Long id) {
        return connectionRepository.findById(id).orElseThrow(() -> new ServiceException("Connection not found"));
    }

    public boolean testConnection(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Connection connection = findConnectionById(id);
        try {
            java.sql.Connection conn = DatabaseConnection.initializeConnection(
                    "Default",
                    connection.getHost(),
                    connection.getPort(),
                    connection.getDatabase(),
                    connection.getUsername(),
                    connection.getPassword(),
                    connection.getDatabaseType(),
                    connection.getDatabaseConnMode());
            DatabaseConnection.closeConnection(conn);
            return true;
        } catch (Exception e) {
            throw new ServiceException("Error testing connection", e);
        }
    }
}
