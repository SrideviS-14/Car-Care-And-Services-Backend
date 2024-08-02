package com.example.backend.repository;

import com.example.backend.model.Query;
import com.example.backend.model.QueryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<Query, Integer> {
}
