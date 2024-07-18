package com.example.backend.controller;

import com.example.backend.model.Booking;
import com.example.backend.model.BookingDto;
import com.example.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Booking book(@RequestBody BookingDto bookingDto) {
        return bookingService.book(bookingDto);
    }

}
