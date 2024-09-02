package com.pedro.jfsql.service;

import com.pedro.jfsql.exception.QueryExceptions;
import com.pedro.jfsql.handler.ConnectionHandler;
import com.pedro.jfsql.model.Connection;
import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.modules.database.DynamicQueryExecutor;
import com.pedro.jfsql.repository.QueryRepository;
import com.pedro.jfsql.util.I18n;
import com.pedro.jfsql.util.I18nMessages;
import jakarta.persistence.Parameter;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return queryRepository.findById(id).orElseThrow(() -> new QueryExceptions.QueryNotFoundException(id));
    }

    public Query createQuery(Query query) {
        return queryRepository.save(query);
    }

    public void deleteQuery(Long id) {
        if (queryRepository.existsById(id)) {
            queryRepository.deleteById(id);
        } else {
            throw new QueryExceptions.QueryNotFoundException(id);
        }
    }

    public List<Map<String, Object>> executeQuery(Long connectionId, String query, List<Parameter> parameters) throws SQLException {
        Connection connectionIdentity = connectionHandler.findConnectionById(connectionId);
        java.sql.Connection connection = initializeConnection(
                "Default",
                connectionIdentity.getHost(),
                connectionIdentity.getPort(),
                connectionIdentity.getDatabase(),
                connectionIdentity.getUsername(),
                connectionIdentity.getPassword(),
                connectionIdentity.getDatabaseType(),
                connectionIdentity.getDatabaseConnMode());
        try {
            return DynamicQueryExecutor.executeQuery(query, connection, parameters);
        } catch (Exception e) {
            throw new ServiceException("Error executing query: " + e);
        }
    }
}
