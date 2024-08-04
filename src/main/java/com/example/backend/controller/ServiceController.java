package com.example.backend.controller;

import com.example.backend.model.Packages;
import com.example.backend.model.Services;
import com.example.backend.repository.ServiceRepository;
import com.example.backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/getAllServicesAndPackages")
    public Iterable<Services> getAllServicesAndPackages() {
        return serviceService.getAllServicesAndPackages();
    }

    @GetMapping("/getAllServices")
    public Iterable<Services> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping("/addService")
    public Iterable<Services> addService(@RequestBody Services service){
        return serviceService.addService(service);
    }

    @DeleteMapping("/deleteService/{serviceId}")
    public Iterable<Services> deleteService(@PathVariable Integer serviceId) {
        serviceRepository.deleteById(serviceId);
        return serviceRepository.findAll();
    }

    @PutMapping("/updateService")
    public String updateService(@RequestBody Services services){
        return serviceService.updateService(services);
    }

    @GetMapping("/getAllPackages")
    public Iterable<Services> getAllPackages() {
        return serviceService.getAllPackages();
    }

    @GetMapping("getAllPackagesWithOffers")
    public Map<Integer, BigDecimal> getAllPackagesWithOffers() {
        return serviceService.getAllPackagesWithOffers();
    }
}
