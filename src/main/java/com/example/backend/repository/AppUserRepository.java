package com.example.backend.repository;

import com.example.backend.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public AppUser findByUserName(String userName);
    public AppUser findByEmail(String email);
}
