package com.pedro.jfsql.service;

import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

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
        return connectionRepository.findById(id).orElse(null);
    }
}
