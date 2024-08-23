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
    @Operation(summary = "All Endpoints", description = "Get all Endpoints")
    public List<Endpoint> getAllEndpoints() {
        return endpointHandler.findAllEndpoints();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint by ID", description = "Get a endpoint by id")
    public Endpoint getEndpointById(@PathVariable Long id) {
        return endpointHandler.findEndpointById(id);
    }

    @PostMapping
    @Operation(summary = "Create Endpoint", description = "Create a new endpoint")
    public void createEndpoint(@RequestBody Endpoint endpoint) {
        endpointHandler.createEndpoint(endpoint);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Endpoint", description = "Delete a endpoint by id")
    public void deleteEndpoint(@PathVariable Long id) {
        endpointHandler.deleteEndpoint(id);
    }

}
