package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryDto {

    @JsonProperty("Query")
    private String Query;

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }
}
