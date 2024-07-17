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

    @DeleteMapping("/deleteService")
    public Iterable<Services> deleteService(@RequestBody Integer service_id) {
        return serviceService.deleteService(service_id);
    }
}
