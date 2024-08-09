package com.example.backend.service;
import com.example.backend.model.AdminBookingDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.backend.model.Booking;
import com.example.backend.model.BookingDto;
import com.example.backend.repository.BookingRepository;
import com.example.backend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    CartService cartService;

    public Iterable<Booking> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> result = new java.util.ArrayList<>(List.of());
        for(Booking booking: bookings) {
                if(!Objects.equals(booking.getStatus(), "completed") && !Objects.equals(booking.getStatus(), "cancelled" )) {
                    result.add(booking);
                }
        }
        return result;
    }

    public Integer book(BookingDto bookingDto) {
        Date date;
        Booking booking = new Booking();
        booking.setService_List(bookingDto.getService_List());
        booking.setPackage_Amount(bookingDto.getPackage_Amount());
        booking.setDateOfBooking(java.sql.Date.valueOf(LocalDate.now()));
        booking.setStatus("confirmed");
        booking.setPaid(bookingDto.isPaid());
        booking.setUser_ID(bookingDto.getUser_ID());
        booking.setTime_Period_In_Days(bookingDto.getTime_Period_In_Days());
        booking.setCar_ID(bookingDto.getCar_ID());
        bookingRepository.save(booking);
        cartService.emptyCart(bookingDto.getUser_ID());
        return booking.getBooking_ID();
    }

    public String updateBookingPayment(int bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        booking.get().setPaid(true);
        bookingRepository.save(booking.orElse(null));
        return "Changed Successfully";
    }

    public Iterable<Booking> getAllBookingsOfUser() {
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> result = new java.util.ArrayList<>(List.of());
        int userID = cartService.getUserIdFromToken();
        for(Booking booking: bookings) {
            if(booking.getUser_ID() == userID) {
                if(!Objects.equals(booking.getStatus(), "completed") && !Objects.equals(booking.getStatus(), "cancelled" )) {
                    result.add(booking);
                    System.out.println(booking.getStatus());
                }
            }
        }
        return result;
    }

    public String updateBookingStatus(int bookingId, String value) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String parsedValue = mapper.readValue(value, String.class);
            booking.get().setStatus(parsedValue);
            bookingRepository.save(booking.orElse(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Changed Successfully";
    }

    public String addBookingAdmin(AdminBookingDto adminBookingDto) {
        return "Completed successfuly";
    }

    public String cancelBooking(int bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        booking.get().setStatus("cancelled");
        bookingRepository.save(booking.orElse(null));
        return "Cancelled booking";
    }

    public Iterable<Booking> getAllBookingsOfUserAll() {
        List<Booking> bookings = bookingRepository.findAll();
        List<Booking> result = new java.util.ArrayList<>(List.of());
        int userID = cartService.getUserIdFromToken();
        for(Booking booking: bookings) {
            if(booking.getUser_ID() == userID) {
                    result.add(booking);
                }
        }
        return result;
    }
}
