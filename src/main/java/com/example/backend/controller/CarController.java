package com.example.backend.controller;

import com.example.backend.model.Car;
import com.example.backend.model.CarDto;
import com.example.backend.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*")
public class CarController {

    @Autowired
    CarService carService;

}
