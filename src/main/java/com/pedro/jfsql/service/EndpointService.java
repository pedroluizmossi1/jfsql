package com.pedro.jfsql.service;

import com.pedro.jfsql.exception.EndpointExceptions;
import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.repository.EndpointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Endpoint> findAllEndpoints() {
        return endpointRepository.findAll();
    }

    public Endpoint findEndpointById(Long id) {
        return endpointRepository.findById(id).orElseThrow(() -> new EndpointExceptions.EndpointNotFoundException(null, id));
    }

    public Optional<Endpoint> findEndpointByPath(String path) {
        Optional<Endpoint> endpoint = endpointRepository.findByEndpoint(path);
        if (endpoint.isEmpty()) {
            throw new EndpointExceptions.EndpointNotFoundException(path, null);
        }
        return endpoint;
    }

    public void deleteEndpoint(Long id) {
        if (endpointRepository.existsById(id)) {
            endpointRepository.deleteById(id);
        } else {
            throw new EndpointExceptions.EndpointNotFoundException(null, id);
        }
    }
}
