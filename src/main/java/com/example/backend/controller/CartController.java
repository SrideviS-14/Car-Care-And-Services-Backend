package com.example.backend.controller;

import com.example.backend.model.Services;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.service.CartService;
import com.example.backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    AppUserRepository appUserRepository;
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addService")
    public Iterable<Services> addServiceToCart(@RequestBody Integer service_id) {
        Integer userId = cartService.getUserIdFromToken();
        return cartService.addServiceToCart(userId, service_id);
    }

    @PostMapping("/removeService")
    public Iterable<Services> removeServiceFromCart(@RequestBody Integer service_id) {
        Integer userId = cartService.getUserIdFromToken();
        return cartService.removeServiceFromCart(userId, service_id);
    }

    @GetMapping("/getServices")
    public Iterable<Services> getServicesInCart() {
        Integer userId = cartService.getUserIdFromToken();
        return cartService.getServicesInCart(userId);
    }

    @GetMapping("/getTotal")
    public String getTotal() {
        Integer userId = cartService.getUserIdFromToken();
        return "Total: ₹" + cartService.getTotal(userId);
    }
}
