package com.example.backend.controller;

import com.example.backend.model.Booking;
import com.example.backend.model.BookingDto;
import com.example.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @GetMapping("/getAllBookings")
    public Iterable<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/book")
    public Integer book(@RequestBody BookingDto bookingDto) {
        return bookingService.book(bookingDto);
    }

    @PutMapping("/updateBookingPayment/{booking_ID}")
    public String updateBookingPayment(@PathVariable int booking_ID){
        return bookingService.updateBookingPayment(booking_ID);
    }

    @PutMapping("/updateBookingStatus/{booking_ID}")
    public String updateBookingStatus(@PathVariable int booking_ID, @RequestBody String value){
        return bookingService.updateBookingStatus(booking_ID, value);
    }

    @GetMapping("/getBookingsOfUser")
    public Iterable<Booking> getAllBookingsOfUser() {
        return bookingService.getAllBookingsOfUser();
    }
}
