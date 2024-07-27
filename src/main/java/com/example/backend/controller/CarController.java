package com.example.backend.controller;

import com.example.backend.model.Car;
import com.example.backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping("/addCarDetails")
    public Iterable<Car> addCarDetails(@RequestBody Car car)
    {
        return carService.addCarDetails(car);
    }

    @GetMapping("/getCarDetailsOfUser")
    public Iterable<Car> getCarDetails(@PathVariable int userID)
    {
        return carService.getCarDetails(userID);
    }
}
