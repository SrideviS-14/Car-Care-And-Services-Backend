package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.Car;
import com.example.backend.model.CarDto;
import com.example.backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

}
