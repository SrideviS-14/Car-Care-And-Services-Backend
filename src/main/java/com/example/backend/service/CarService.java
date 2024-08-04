package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.Car;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public Car addCarDetails(Car car) {
        AppUser appUser = appUserRepository.findById(car.getAppUser().getId()).orElseThrow(() -> new RuntimeException("User not found"));
        car.setAppUser(appUser);
        return carRepository.save(car);
    }

    public Iterable<Car> getCarDetails(int userID) {
        List<Car> cars = carRepository.findAll();
        List<Car> result = new ArrayList<>(List.of());
        for(Car car: cars)
        {
            if(car.getAppUser().getId() == userID)
            {
                result.add(car);
            }
        }
        System.out.println(result);
        return result;
    }
}
