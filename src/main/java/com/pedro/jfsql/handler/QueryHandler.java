package com.pedro.jfsql.handler;

import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.service.QueryService;
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

    public void createQueryHandler(Query query) {
        queryService.createQuery(query);
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

    public List<Map<String, Object>> executeQueryHandler(String connectionId, String query) {
        return queryService.executeQuery(connectionId, query);
    }
}
