package com.example.backend.service;

import com.example.backend.model.Payment;
import com.example.backend.model.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    Payment payment;

    public void makePayment(PaymentDto paymentDto) {
        payment.setPayment_ID(paymentDto.getPayment_ID());
        payment.setPayment_Type(paymentDto.getPayment_Type());
        payment.setBooking_ID(paymentDto.getBooking_ID());
        payment.setCard_Number(paymentDto.getCard_Number());
        payment.setCard_Holder_Name(paymentDto.getCard_Holder_Name());
        payment.setExpiration_Date(paymentDto.getExpiration_Date());
    }
}
