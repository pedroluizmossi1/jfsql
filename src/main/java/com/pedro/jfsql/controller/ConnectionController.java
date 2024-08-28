package com.pedro.jfsql.controller;

import com.pedro.jfsql.controller.dto.ConnectionStatusResponse;
import com.pedro.jfsql.handler.ConnectionHandler;
import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.model.enumeration.DatabaseType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connections")
@Tag(name = "Connections API", description = "API for managing connections")
public class ConnectionController {

    private final ConnectionHandler connectionHandler;

    @Autowired
    public ConnectionController(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }


    @GetMapping
    @Operation(summary = "All Connections", description = "Get all connections")
    public List<Connection> getAllConnections() {
        return connectionHandler.findAllConnections();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Connection by ID", description = "Get a connection by id")
    public Connection getConnectionById(@PathVariable Long id) {
        return connectionHandler.findConnectionById(id);
    }

    @PostMapping
    @Operation(summary = "Create Connection", description = "Create a new connection")
    public ConnectionStatusResponse createConnection(@RequestBody Connection connection) {
        connectionHandler.createConnectionHandler(connection);
        return new ConnectionStatusResponse(connection.getId(), "Connection created successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Connection", description = "Delete a connection by id")
    public ConnectionStatusResponse deleteConnection(@PathVariable Long id) {
        connectionHandler.deleteConnection(id);
        return new ConnectionStatusResponse(id, "Connection deleted successfully");
    }

    @GetMapping("/databaseTypes")
    @Operation(summary = "Database Types", description = "Get all database types")
    public DatabaseType[] getDatabaseTypes() {
        return connectionHandler.getDatabaseTypes();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Connection", description = "Update a connection")
    public ConnectionStatusResponse updateConnection(@PathVariable Long id, @RequestBody Connection connection) {
        connection.setId(id);
        connectionHandler.createConnectionHandler(connection);
        return new ConnectionStatusResponse(id, "Connection updated successfully");
    }

    @GetMapping("/test/{id}")
    @Operation(summary = "Test Connection", description = "Test a connection")
    public ConnectionStatusResponse testConnection(@PathVariable Long id) {
        Connection connection = connectionHandler.findConnectionById(id);
        connectionHandler.testConnection(connection.getId());
        return new ConnectionStatusResponse(id, "Connection tested successfully");
    }

}