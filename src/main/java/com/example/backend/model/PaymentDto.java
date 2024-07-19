package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDto {

    @JsonProperty("Booking_ID")
    private int Booking_ID;

    @JsonProperty("Payment_Type")
    private String Payment_Type;

    @JsonProperty("Card_Holder_Name")
    private String Card_Holder_Name;

    @JsonProperty("Card_Number")
    private String Card_Number;

    @JsonProperty("Expiration_Date")
    private String Expiration_Date;

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

