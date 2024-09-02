package com.pedro.jfsql.handler;

import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.service.QueryService;
import jakarta.persistence.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class QueryHandler {

    private final QueryService queryService;

    @Autowired
    public QueryHandler(QueryService queryService) {
        this.queryService = queryService;
    }

    public Query createQueryHandler(Query query) {
        return queryService.createQuery(query);
    }

    public List<Query> findAllQueries() {
        return queryService.findAllQueries();
    }

    public Query findQueryById(Long id) {
        return queryService.findQueryById(id);
    }

    public void deleteQueryHandler(Long id) {
        queryService.deleteQuery(id);
    }

    public Query updateQueryHandler(Long id, Query query) {
        return queryService.updateQuery(id, query);
    }

    public List<Map<String, Object>> executeQueryHandler(Long connectionId, String query, List<Parameter> parameters) throws Exception {
        return queryService.executeQuery(connectionId, query, parameters);
    }
}
