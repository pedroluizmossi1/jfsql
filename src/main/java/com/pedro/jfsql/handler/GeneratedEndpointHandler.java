package com.pedro.jfsql.handler;

import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.service.GeneratedEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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

    public List<Map<String, Object>> processEndpoint(Endpoint endpoint) throws Exception {
        return generatedEndpointService.processEndpoint(endpoint);
    }
}
