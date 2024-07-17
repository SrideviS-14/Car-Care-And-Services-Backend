package com.example.backend.service;

import com.example.backend.model.Services;
import com.example.backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    public Iterable<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    public Iterable<Services> addService(Services service) {
        serviceRepository.save(service);
        return serviceRepository.findAll();
    }

    public Iterable<Services> deleteService(Integer serviceId) {
        serviceRepository.deleteById(serviceId);
        return serviceRepository.findAll();
    }
}
