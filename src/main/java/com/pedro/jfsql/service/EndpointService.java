package com.pedro.jfsql.service;

import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.repository.EndpointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EndpointService {

    private final EndpointRepository endpointRepository;

    @Autowired
    public EndpointService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
    }

    public void createEndpoint(Endpoint endpoint) {
        endpointRepository.save(endpoint);
    }

    public Endpoint getEndpoint(Long id) {
        return endpointRepository.findById(id).orElse(null);
    }

    public List<Endpoint> findAllEndpoints() {
        return endpointRepository.findAll();
    }

    public Endpoint findEndpointById(Long id) {
        return endpointRepository.findById(id).orElse(null);
    }

    public void deleteEndpoint(Long id) {
        endpointRepository.deleteById(id);
    }
}
