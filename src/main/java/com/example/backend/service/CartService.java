package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.Services;
import com.example.backend.repository.ServiceRepository;
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
        boolean flag = false;
        for(Services services: getServicesInCart(userId))
        {
            if(services.getService_ID() == service_id)
            {
                flag = true;
            }
        }
        if (!flag) {
            userCarts.computeIfAbsent(userId, k -> new ArrayList<>()).add(service.get());
        }
        return userCarts.getOrDefault(userId, new ArrayList<>());
    }

    public Iterable<Services> removeServiceFromCart(Integer userId, Integer service_id) {
        Optional<Services> service = serviceRepository.findById(service_id);
        for(Services services: getServicesInCart(userId))
        {
            if(services.getService_ID() == service_id)
            {
                userCarts.computeIfAbsent(userId, k -> new ArrayList<>()).remove(services);
            }
        }
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

    public void addQuickService(List<Integer> serviceList) {
        for(Integer serviceId: serviceList)
        {
            addServiceToCart(getUserIdFromToken(), serviceId);
        }
    }
}
