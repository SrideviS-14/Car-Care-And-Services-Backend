package com.example.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name="Queries")
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Query_ID;

    @Column(name = "Query")
    private String Query;

    @Column(name = "IsAddressed")
    private boolean IsAddressed;

    @Column(name="User_ID")
    private int User_ID;

    public int getQueries_ID() {
        return Query_ID;
    }

    public void setQueries_ID(int queries_ID) {
        Query_ID = queries_ID;
    }

    public String getQuery() {
        return Query;
    }

    public void setQuery(String query) {
        Query = query;
    }

    public boolean isAddressed() {
        return IsAddressed;
    }

    public void setAddressed(boolean addressed) {
        IsAddressed = addressed;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }
}

