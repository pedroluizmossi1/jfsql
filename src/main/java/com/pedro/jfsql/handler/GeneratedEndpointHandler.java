package com.pedro.jfsql.handler;

import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.service.GeneratedEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GeneratedEndpointHandler {

    private final GeneratedEndpointService generatedEndpointService;

    @Autowired
    public GeneratedEndpointHandler(GeneratedEndpointService generatedEndpointService) {
        this.generatedEndpointService = generatedEndpointService;
    }

    public Optional<Endpoint> getEndpointData(String endpoint) {
        return generatedEndpointService.getEndpointData(endpoint);
    }

    public String processEndpoint(Endpoint endpoint) {
        return generatedEndpointService.processEndpoint(endpoint);
    }
}
