package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.Services;
import com.example.backend.repository.ServiceRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CartService {

    @Autowired
    ServiceRepository serviceRepository;

    private final Map<Integer, List<Services>> userCarts = new HashMap<>();

    public Integer getUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            AppUser userDetails = (AppUser) authentication.getPrincipal();
            return userDetails.getId();
        }
        return null;
    }

    public Iterable<Services> addServiceToCart(Integer userId, Integer service_id) {
        Optional<Services> service = serviceRepository.findById(service_id);

        if (service.isPresent()) {
            if (!userCarts.getOrDefault(userId, new ArrayList<>()).contains(service.get())) {
                userCarts.computeIfAbsent(userId, k -> new ArrayList<>()).add(service.get());
            }
        }

        return userCarts.getOrDefault(userId, new ArrayList<>());
    }

    public Iterable<Services> removeServiceFromCart(Integer userId, Integer service_id) {
        Optional<Services> service = serviceRepository.findById(service_id);
        userCarts.getOrDefault(userId, new ArrayList<>()).remove(service.orElse(null));
        return userCarts.getOrDefault(userId, new ArrayList<>());
    }

    public List<Services> getServicesInCart(Integer userId) {
        return userCarts.getOrDefault(userId, new ArrayList<>());
    }

    public BigDecimal getTotal(Integer userId) {
        List<Services> services = getServicesInCart(userId);
        BigDecimal total = BigDecimal.ZERO;
        for (Services service : services) {
            total = total.add(service.getService_Amount());
        }
        return total;
    }

    public void checkout(Integer userId) {
        // Implement checkout logic here (e.g., process payment, clear cart, etc.)
        // ...
    }
}
