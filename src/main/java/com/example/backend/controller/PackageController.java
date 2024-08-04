package com.example.backend.controller;

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

    @PostMapping("/buyPackage")
    public String buyPackage(@RequestBody int packageID) {
        return cartService.buyPackage(packageID);
    }

}
