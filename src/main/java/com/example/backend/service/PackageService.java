package com.example.backend.service;

import com.example.backend.model.Packages;
import com.example.backend.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;

    public Iterable<Packages> getAllPackages() {
        return packageRepository.findAll();
    }
}
