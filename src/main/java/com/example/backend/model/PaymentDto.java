package com.example.backend.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class PaymentDto {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Payment_ID;

    private int Booking_ID;

    private String Payment_Type;

    private String Card_Holder_Name;

    private String Card_Number;

    private String Expiration_Date;

    public int getPayment_ID() {
        return Payment_ID;
    }

    public void setPayment_ID(int payment_ID) {
        Payment_ID = payment_ID;
    }

    public int getBooking_ID() {
        return Booking_ID;
    }

    public void setBooking_ID(int booking_ID) {
        Booking_ID = booking_ID;
    }

    public String getPayment_Type() {
        return Payment_Type;
    }

    public void setPayment_Type(String payment_Type) {
        Payment_Type = payment_Type;
    }

    public String getCard_Holder_Name() {
        return Card_Holder_Name;
    }

    public void setCard_Holder_Name(String card_Holder_Name) {
        Card_Holder_Name = card_Holder_Name;
    }

    public String getCard_Number() {
        return Card_Number;
    }

    public void setCard_Number(String card_Number) {
        Card_Number = card_Number;
    }

    public String getExpiration_Date() {
        return Expiration_Date;
    }

    public void setExpiration_Date(String expiration_Date) {
        Expiration_Date = expiration_Date;
    }
}

