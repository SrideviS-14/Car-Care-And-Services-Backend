package com.example.backend.controller;

import com.example.backend.model.PackageAmountUpdateDto;
import com.example.backend.model.PackageTimePeriodUpdateDto;
import com.example.backend.model.Packages;
import com.example.backend.service.CartService;
import com.example.backend.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package")
@CrossOrigin(origins = "*")
public class PackageController {

    @Autowired
    PackageService packageService;

    @Autowired
    CartService cartService;

    @GetMapping("/getAllPackages")
    public Iterable<Packages> getAllPackages() {
        return  packageService.getAllPackages();
    }

    @PostMapping("/updatePackageAmount")
    public Packages updatePackageAmount(@RequestBody PackageAmountUpdateDto packageAmountUpdateDto) {
        return packageService.updatePackageAmount(packageAmountUpdateDto.getPackageID(), packageAmountUpdateDto.getAmount());
    }

    @PostMapping("/updateServiceList")
    public Packages updateServiceList(@RequestBody int package_ID, String serviceList) {
        return packageService.updateServiceList(package_ID, serviceList);
    }

    @PostMapping("/updateTimePeriod")
    public Packages updateTimePeriod(@RequestBody PackageTimePeriodUpdateDto packageTimePeriodUpdateDto) {
        return packageService.updateTimePeriod(packageTimePeriodUpdateDto.getPackageID(), packageTimePeriodUpdateDto.getTime_Period_In_Days());
    }

    @PostMapping("/buyPackage")
    public String buyPackage(@RequestBody int packageID) {
        return cartService.buyPackage(packageID);
    }

}
