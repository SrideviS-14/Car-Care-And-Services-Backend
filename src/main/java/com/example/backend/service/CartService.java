package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.Packages;
import com.example.backend.model.Services;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.repository.PackageRepository;
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

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    AppUserRepository appUserRepository;
    
    private final Map<Integer, List<Services>> userCarts = new HashMap<>();

    public int getUserIdFromToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        AppUser appUser = appUserRepository.findByUserName(username);
        return appUser.getId();
    }

    public Iterable<Services> addServiceToCart(Integer userId, Integer service_id) {
        Optional<Services> service = serviceRepository.findById(service_id);
        System.out.println(userId);
        List<Services> servicesInCart = new ArrayList<>(userCarts.getOrDefault(userId, new ArrayList<>()));
        for(Services services: servicesInCart)
        {
            if(!(services.isService()))
            {
                // Remove existing services from the cart
                removeServiceFromCart(userId, services.getService_ID());
            }
        }
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
        List<Services> servicesInCart = userCarts.getOrDefault(userId, new ArrayList<>());
        Iterator<Services> iterator = servicesInCart.iterator();

        // Iterate over the list
        while (iterator.hasNext()) {
            Services services = iterator.next();
            if (services.getService_ID() == service_id) {
                iterator.remove();  // Safely remove the element from the list
            }
        }
        return servicesInCart;
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
        Integer userID = getUserIdFromToken();
        List<Services> servicesInCart = new ArrayList<>(userCarts.getOrDefault(userID, new ArrayList<>()));
        Iterator<Services> iterator = servicesInCart.iterator();
        while (iterator.hasNext()) {
            Services services = iterator.next();
            removeServiceFromCart(userID, services.getService_ID());
        }
        for (Integer serviceId : serviceList) {
            addServiceToCart(userID, serviceId);
        }
    }

    public String buyPackage(int packageID) {
        Integer userID = getUserIdFromToken();
        Optional<Services> service = serviceRepository.findById(packageID);
        List<Services> servicesInCart = new ArrayList<>(userCarts.getOrDefault(userID, new ArrayList<>()));
        Iterator<Services> iterator = servicesInCart.iterator();
        // Remove existing services from the cart
        while (iterator.hasNext()) {
            Services services = iterator.next();
            removeServiceFromCart(userID, services.getService_ID());
        }
        addServiceToCart(userID, service.get().getService_ID());
        return "Added Successfully";
    }

    public void emptyCart(int userID){
        System.out.println(userID);
        userCarts.remove(userID);
    }
}