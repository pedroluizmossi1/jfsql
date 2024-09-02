package com.pedro.jfsql.service;

import com.pedro.jfsql.handler.QueryHandler;
import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.model.Endpoint;
import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.repository.EndpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GeneratedEndpointService {

    private final EndpointRepository endpointRepository;
    private final QueryHandler queryHandler;

    @Autowired
    public GeneratedEndpointService(EndpointRepository endpointRepository, QueryHandler queryHandler) {
        this.endpointRepository = endpointRepository;
        this.queryHandler = queryHandler;
    }

    public Optional<Endpoint> getEndpointData(String endpoint) {
        return endpointRepository.findByEndpoint(endpoint);
    }

    public List<Map<String, Object>> processEndpoint(Endpoint endpoint) throws Exception {
        Query query = endpoint.getQuery();
        Connection connection = endpoint.getConnection();
        return queryHandler.executeQueryHandler(connection.getId(), query.getQuery(), query.getParameters());
    }
}