package com.example.backend.controller;

import com.example.backend.model.Query;
import com.example.backend.model.QueryDto;
import com.example.backend.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/query")
@CrossOrigin(origins = "*")
public class QueryController {

    @Autowired
    QueryService queryService;

    @PostMapping("/addQuery")
    public String addQuery(@RequestBody QueryDto queryDto)
    {
        return queryService.addQuery(queryDto);
    }

    @GetMapping("getAllQueries")
    public Iterable<Query> getQueries()
    {
        return queryService.getQueries();
    }
}
