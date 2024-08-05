package com.pedro.jfsql.service;

import com.pedro.jfsql.model.Query;
import com.pedro.jfsql.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    private final QueryRepository queryRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
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


}
