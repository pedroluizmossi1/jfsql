package com.pedro.jfsql.controller;

import com.pedro.jfsql.controller.dto.QueryRequest;
import com.pedro.jfsql.handler.QueryHandler;
import com.pedro.jfsql.model.Query;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/queries")
@Tag(name = "Queries API", description = "API for managing queries")
public class QueryController {

    private final QueryHandler queryHandler;

    @Autowired
    public QueryController(QueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping
    @Operation(summary = "All Queries", description = "Get all queries")
    public List<Query> getAllQueries() {
        return queryHandler.findAllQueries();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Query by ID", description = "Get a query by id")
    public Query getQueryById(@PathVariable Long id) {
        return queryHandler.findQueryById(id);
    }

    @PostMapping
    @Operation(summary = "Create Query", description = "Create a new query")
    public void createQuery(@RequestBody Query query) {
        queryHandler.createQueryHandler(query);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Query", description = "Delete a query by id")
    public void deleteQuery(@PathVariable Long id) {
        queryHandler.deleteQueryHandler(id);
    }

    @PostMapping("/execute/{connectionId}")
    @Operation(summary = "Execute Query", description = "Execute a query")
    public List<Map<String, Object>> executeQuery(@RequestBody QueryRequest queryRequest, @PathVariable Long connectionId) throws Exception {
        return queryHandler.executeQueryHandler(connectionId, queryRequest.getQuery(), queryRequest.getParameters());
    }
}

