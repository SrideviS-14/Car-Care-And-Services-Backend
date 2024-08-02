package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Date;

public class AdminBookingDto {
    @JsonProperty("Service_List")
    private String Service_List;

    @JsonProperty("Package_Amount")
    private BigDecimal Package_Amount;

    @JsonProperty("Time_Period_In_Days")
    private int Time_Period_In_Days;

    @JsonProperty("Paid")
    private boolean Paid;

    @JsonProperty("DateOfBooking")
    private Date DateOfBooking;

    @JsonProperty("Status")
    private String Status;

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("PhoneNumber")
    private String PhoneNumber;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getService_List() {
        return Service_List;
    }

    public void setService_List(String service_List) {
        Service_List = service_List;
    }

    public BigDecimal getPackage_Amount() {
        return Package_Amount;
    }

    public void setPackage_Amount(BigDecimal package_Amount) {
        Package_Amount = package_Amount;
    }

    public int getTime_Period_In_Days() {
        return Time_Period_In_Days;
    }

    public void setTime_Period_In_Days(int time_Period_In_Days) {
        Time_Period_In_Days = time_Period_In_Days;
    }


    public boolean isPaid() {
        return Paid;
    }

    public void setPaid(boolean paid) {
        Paid = paid;
    }

    public Date getDateOfBooking() {
        return DateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        DateOfBooking = dateOfBooking;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

