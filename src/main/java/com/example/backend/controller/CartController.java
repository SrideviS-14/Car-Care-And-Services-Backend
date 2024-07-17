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
public class CartController {

    @Autowired
    AppUserRepository appUserRepository;
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/")
    public ResponseEntity<Object> profile(Authentication auth) {
        var response = new HashMap<String, Object>();
        response.put("Username", auth.getDetails());

        var appUser = appUserRepository.findByUserName(auth.getName());
        response.put("User", appUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addService")
    public Iterable<Services> addServiceToCart(@RequestBody Integer service_id) {
        Integer userId = cartService.getUserIdFromToken();
        return cartService.addServiceToCart(userId, service_id);
    }

    @PostMapping("/removeService")
    public Iterable<Services> removeServiceFromCart(@RequestBody Integer service_id) {
        Integer userId = cartService.getUserIdFromToken();
        List<Services> servicesInCart = cartService.getServicesInCart(userId);
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
        return "Total: $" + cartService.getTotal(userId);
    }
}
