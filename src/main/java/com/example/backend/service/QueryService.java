package com.example.backend.service;

import com.example.backend.model.Query;
import com.example.backend.model.QueryDto;
import com.example.backend.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    CartService cartService;

    public String addQuery(QueryDto queryDto) {
        Query query = new Query();
        query.setQuery(queryDto.getQuery());
        query.setAddressed(false);
        query.setUser_ID(cartService.getUserIdFromToken());
        queryRepository.save(query);
        return "Added successfully";
    }

    public Iterable<Query> getQueries() {
        return queryRepository.findAll();
    }
}
