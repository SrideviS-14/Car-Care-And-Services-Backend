package com.example.backend.controller;

import com.example.backend.model.Booking;
import com.example.backend.model.Services;
import com.example.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "*")
public class DashBoardController {

    @Autowired
    BookingRepository bookingRepository;

    @GetMapping("/getCountOfStatus")
    public Map<String, Integer> getCountOfStatus() {
        Map<String, Integer> StatusTrack = new HashMap<>();
        List<Booking> bookings = bookingRepository.findAll();
        Integer countPending = 0;
        Integer countConfirmed = 0;
        Integer countCompleted = 0;
        for(Booking booking: bookings)
        {
            if(Objects.equals(booking.getStatus(), "pending"))
            {
                countPending++;
            }
            else if(Objects.equals(booking.getStatus(), "confirmed"))
            {
                countConfirmed++;
            }
            else if(Objects.equals(booking.getStatus(), "completed"))
            {
                countCompleted++;
            }
        }
        StatusTrack.put("Pending", countPending);
        StatusTrack.put("Confirmed", countConfirmed);
        StatusTrack.put("Completed", countCompleted);

        return StatusTrack;
    }

    @GetMapping("/getPackageStatus")
    public Map<String, Integer> getPackageStatus() {
        Map<String, Integer> StatusTrack = new HashMap<>();
        List<Booking> bookings = bookingRepository.findAll();
        Integer countStandard = 0;
        Integer countClassic = 0;
        Integer countPremium = 0;
        for(Booking booking: bookings)
        {
            if(Objects.equals(booking.getService_List(), "9"))
            {
                countStandard++;
            }
            else if(Objects.equals(booking.getService_List(), "10"))
            {
                countClassic++;
            }
            else if(Objects.equals(booking.getService_List(), "11"))
            {
                countPremium++;
            }
        }
        StatusTrack.put("Standard", countStandard);
        StatusTrack.put("Classic", countClassic);
        StatusTrack.put("Premium", countPremium);

        return StatusTrack;
    }

    @GetMapping("/getMonthlyOrders")
    public Map<String, Integer> getMonthlyOrders() {
        Map<String, Integer> monthlyOrders = new HashMap<>();
        List<Booking> bookings = bookingRepository.findAll();

        // Initialize the map with months
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            monthlyOrders.put(month, 0);
        }

        // Count the number of orders in each month
        for (Booking booking : bookings) {
            // Assuming getDateOfBooking() returns a java.util.Date object
            int month = Integer.parseInt(new java.text.SimpleDateFormat("MM").format(booking.getDateOfBooking())) - 1;
            monthlyOrders.put(months[month], monthlyOrders.get(months[month]) + 1);
        }

        return monthlyOrders;
    }

    @GetMapping("/getTotalSales")
    public BigDecimal getTotalSales() {
        List<Booking> bookings = bookingRepository.findAll();
        BigDecimal total = new BigDecimal("0.0");
        for(Booking booking: bookings)
        {
            total = total.add(booking.getPackage_Amount());
        }
        return total;
    }


}