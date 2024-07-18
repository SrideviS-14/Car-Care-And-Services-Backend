package com.example.backend.service;

import com.example.backend.model.Booking;
import com.example.backend.model.BookingDto;
import com.example.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    Booking booking;

    public Iterable<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking book(BookingDto bookingDto) {
        booking.setBooking_ID(bookingDto.getBooking_ID());
        booking.setService_Type(bookingDto.getService_Type());
        booking.setService_List(bookingDto.getService_List());
        booking.setPackage_Amount(bookingDto.getPackage_Amount());
        booking.setActive(bookingDto.isActive());
        booking.setPaid(bookingDto.isPaid());
        booking.setTime_Period_In_Days(bookingDto.getTime_Period_In_Days());
        booking.setUserID(bookingDto.getUserID());
        return booking;
    }
}
