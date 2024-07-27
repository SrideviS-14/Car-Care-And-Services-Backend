package com.example.backend.service;

import com.example.backend.model.Services;
import com.example.backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    public Iterable<Services> getAllServices() {
        List<Services> services = serviceRepository.findAll();
        List<Services> packages = new java.util.ArrayList<>(List.of());
        for(Services service: services) {
            if(service.isService()) {
                packages.add(service);
            }
        }
        return packages;
    }

    public Iterable<Services> addService(Services service) {
        serviceRepository.save(service);
        return serviceRepository.findAll();
    }

    public Iterable<Services> deleteService(Integer serviceId) {
        serviceRepository.deleteById(serviceId);
        return serviceRepository.findAll();
    }
    public String updateService(Services services) {
        Optional<Services> oldService = serviceRepository.findById(services.getService_ID());
        oldService.get().setService_Amount(services.getService_Amount());
        oldService.get().setService_Name(services.getService_Name());
        oldService.get().setDescription(services.getDescription());
        serviceRepository.save(oldService.orElse(null));
        return "Update Successful";
    }

    public Iterable<Services> getAllPackages() {
        List<Services> services = serviceRepository.findAll();
        List<Services> packages = new java.util.ArrayList<>(List.of());
        for(Services service: services) {
            if(!(service.isService())) {
                packages.add(service);
            }
        }
        return packages;
    }

    public Iterable<Services> getAllServicesAndPackages() {
        return serviceRepository.findAll();
    }
}
