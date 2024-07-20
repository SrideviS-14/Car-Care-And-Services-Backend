package com.example.backend.service;

import com.example.backend.model.Packages;
import com.example.backend.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    PackageRepository packageRepository;

    public Iterable<Packages> getAllPackages() {
        return packageRepository.findAll();
    }

    public Packages updatePackageAmount(int packageId, BigDecimal amount) {
        Optional<Packages> packages = packageRepository.findById(packageId);
        packages.get().setPackage_Amount(amount);
        return packageRepository.save(packages.orElse(null));
    }

    public Packages updateServiceList(int packageId, String serviceList) {
        Optional<Packages> packages = packageRepository.findById(packageId);
        packages.get().setService_List(serviceList);
        return packageRepository.save(packages.orElse(null));
    }

    public Packages updateTimePeriod(int packageId, Integer timePeriodInDays) {
        Optional<Packages> packages = packageRepository.findById(packageId);
        packages.get().setTime_Period_In_Days(timePeriodInDays);
        return packageRepository.save(packages.orElse(null));
    }
    
}
