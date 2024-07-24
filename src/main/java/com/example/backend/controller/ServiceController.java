package com.example.backend.controller;

import com.example.backend.model.Services;
import com.example.backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/service")
@CrossOrigin(origins = "*")
public class ServiceController {

    @Autowired
    ServiceService serviceService;

    @GetMapping("/getAllServices")
    public Iterable<Services> getAllServices() {
        return serviceService.getAllServices();
    }

    @PostMapping("/addService")
    public Iterable<Services> addService(@RequestBody Services service){
        return serviceService.addService(service);
    }

    @DeleteMapping("/deleteService/{service_ID}")
    public Iterable<Services> deleteService(@PathVariable Integer service_id) {
        return serviceService.deleteService(service_id);
    }

    @PutMapping("/updateService")
    public String updateService(@RequestBody Services services){
        return serviceService.updateService(services);
    }

    @GetMapping("/getAllPackages")
    public Iterable<Services> getAllPackages() {
        return serviceService.getAllPackages();
    }
}
