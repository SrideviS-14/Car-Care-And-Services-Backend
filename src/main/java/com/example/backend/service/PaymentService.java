package com.example.backend.service;

import com.example.backend.model.Booking;
import com.example.backend.model.Payment;
import com.example.backend.model.PaymentDto;
import com.example.backend.repository.BookingRepository;
import com.example.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    PaymentRepository paymentRepository;

    public void makePayment(PaymentDto paymentDto) {
        Payment payment = new Payment();
        payment.setPayment_Type(paymentDto.getPayment_Type());
        payment.setBooking_ID(paymentDto.getBooking_ID());
        payment.setCard_Number(paymentDto.getCard_Number());
        payment.setCard_Holder_Name(paymentDto.getCard_Holder_Name());
        payment.setExpiration_Date(paymentDto.getExpiration_Date());
        paymentRepository.save(payment);
        int booking_id = payment.getBooking_ID();
        Optional<Booking> booking = bookingRepository.findById(booking_id);
        booking.get().setPaid(true);
        bookingRepository.save(booking.orElse(null));
    }
}
