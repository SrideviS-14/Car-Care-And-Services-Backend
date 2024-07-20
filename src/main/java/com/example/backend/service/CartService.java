package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.Packages;
import com.example.backend.model.Services;
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
        List<Services> servicesInCart = new ArrayList<>(userCarts.getOrDefault(userID, new ArrayList<>()));
        Iterator<Services> iterator = servicesInCart.iterator();
        Optional<Packages> packages = packageRepository.findById(packageID);
        String servicesList = packages.get().getService_List();
        List<String> serviceList = List.of(servicesList.split(", "));

        // Remove existing services from the cart
        while (iterator.hasNext()) {
            Services services = iterator.next();
            removeServiceFromCart(userID, services.getService_ID());
        }

        // Add services from the package to the cart
        for (String service : serviceList) {
            for (Services existingService : serviceRepository.findAll()) {
                if (existingService.getService_Name().equals(service)) {
                    addServiceToCart(userID, existingService.getService_ID());
                    break;// Exit the inner loop once the service is added
                }
            }
        }

        return "Added Successfully";
    }

}
