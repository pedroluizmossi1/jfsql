package com.pedro.jfsql.handler;

import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.service.EndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EndpointHandler {

    private final EndpointService endpointService;

    @Autowired
    public EndpointHandler(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    public void createEndpoint(Endpoint endpoint) {
        endpointService.createEndpoint(endpoint);
    }

    public List<Endpoint> findAllEndpoints() {
        return endpointService.findAllEndpoints();
    }

    public Endpoint findEndpointById(Long id) {
        return endpointService.findEndpointById(id);
    }

    public Optional<Endpoint> findEndpointByPath(String path) {
        return endpointService.findEndpointByPath(path);
    }

    public void deleteEndpoint(Long id) {
        endpointService.deleteEndpoint(id);
    }
}
