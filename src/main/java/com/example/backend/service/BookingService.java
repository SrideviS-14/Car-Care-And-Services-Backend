package com.example.backend.service;

import com.example.backend.model.AppUser;
import com.example.backend.model.Booking;
import com.example.backend.model.BookingDto;
import com.example.backend.model.Services;
import com.example.backend.repository.BookingRepository;
import com.example.backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ServiceRepository serviceRepository;

    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public String book(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setService_Type(bookingDto.getService_Type());
        booking.setService_List(bookingDto.getService_List());
        booking.setPackage_Amount(bookingDto.getPackage_Amount());
        booking.setActive(bookingDto.isActive());
        booking.setPaid(bookingDto.isPaid());
        booking.setUser_ID(bookingDto.getUser_ID());
        booking.setTime_Period_In_Days(bookingDto.getTime_Period_In_Days());
        bookingRepository.save(booking);
        return "Added Successfully";
    }

}
