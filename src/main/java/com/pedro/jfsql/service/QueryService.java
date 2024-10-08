package com.pedro.jfsql.service;

import com.pedro.jfsql.handler.ConnectionHandler;
import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.modules.database.DynamicQueryExecutor;
import com.pedro.jfsql.repository.QueryRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.pedro.jfsql.modules.database.DatabaseConnection.initializeConnection;

@Service
public class QueryService {

    private final QueryRepository queryRepository;
    private final ConnectionHandler connectionHandler;
    @Autowired
    public QueryService(QueryRepository queryRepository, ConnectionHandler connectionHandler) {
        this.queryRepository = queryRepository;
        this.connectionHandler = connectionHandler;
    }

    public List<Query> findAllQueries() {
        return queryRepository.findAll();
    }

    public Query findQueryById(Long id) {
        return queryRepository.findById(id).orElse(null);
    }

    public Query createQuery(Query query) {
        return queryRepository.save(query);
    }

    public void deleteQuery(Long id) {
        queryRepository.deleteById(id);
    }


    public List<Map<String, Object>> executeQuery(String connectionId, String query, List<Object> parameters) throws SQLException {
        Connection connectionIdentity = connectionHandler.findConnectionById(Long.parseLong(connectionId));
        java.sql.Connection connection = initializeConnection("Default", connectionIdentity.getHost(), connectionIdentity.getPort(), connectionIdentity.getDatabase(), connectionIdentity.getUsername(), connectionIdentity.getPassword(), connectionIdentity.getDatabaseType());
        try {
            return DynamicQueryExecutor.executeQuery(query, connection, parameters);
        } catch (Exception e) {
            throw new ServiceException("Error executing query: " + e);
        }
    }
}
