package com.pedro.jfsql.controller;

import com.pedro.jfsql.handler.EndpointHandler;
import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.repository.EndpointRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endpoints")
@Tag(name = "Endpoints API", description = "API for managing endpoints")
public class EndpointController {

    @Autowired
    private EndpointHandler endpointHandler;

    @GetMapping
    @Operation(summary = "All Connections", description = "Get all connections")
    public List<Endpoint> getAllEndpoints() {
        return endpointHandler.findAllEndpoints();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Connection by ID", description = "Get a connection by id")
    public Endpoint getEndpointById(@PathVariable Long id) {
        return endpointHandler.findEndpointById(id);
    }

    @PostMapping
    @Operation(summary = "Create Connection", description = "Create a new connection")
    public void createEndpoint(@RequestBody Endpoint endpoint) {
        endpointHandler.createEndpoint(endpoint);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Connection", description = "Delete a connection by id")
    public void deleteEndpoint(@PathVariable Long id) {
        endpointHandler.deleteEndpoint(id);
    }

}
