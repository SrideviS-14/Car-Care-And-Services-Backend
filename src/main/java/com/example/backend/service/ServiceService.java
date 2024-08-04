package com.example.backend.service;

import com.example.backend.model.Packages;
import com.example.backend.model.Services;
import com.example.backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<Integer, BigDecimal> getAllPackagesWithOffers() {
        List<Services> services = serviceRepository.findAll();
        List<Services> packages = new java.util.ArrayList<>(List.of());
        Map<Integer, BigDecimal> packageOffers = new HashMap<>();
        for(Services service: services) {
            if(!(service.isService())) {
                packages.add(service);
            }
        }
        for(Services service: packages){
            List<String> serviceList = List.of(service.getDescription().split(", "));
            BigDecimal total = BigDecimal.ZERO;
            for(String serviceName: serviceList){
                for(Services service1: services){
                    if(service1.getService_Name().equals(serviceName)){
                        total = total.add(service1.getService_Amount());
                    }
                }
            }
            BigDecimal offerAmount = total;
            packageOffers.put(service.getService_ID(), offerAmount);
        }
        System.out.println(packageOffers);
        return packageOffers;
    }
}
