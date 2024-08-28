package com.pedro.jfsql.service;

import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneratedEndpointService {

    private final EndpointRepository endpointRepository;

    @Autowired
    public GeneratedEndpointService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    public Optional<Endpoint> getEndpointData(String endpoint) {
        return endpointRepository.findByEndpoint(endpoint);
    }

    public String processEndpoint(Endpoint endpoint) {
        return "Processed endpoint: " + endpoint.getDescription();
    }
}