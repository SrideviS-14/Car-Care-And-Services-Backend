package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.sql.Date;

public class BookingDto {

    @JsonProperty("Service_Type")
    private String Service_Type;

    @JsonProperty("Service_List")
    private String Service_List;

    @JsonProperty("Package_Amount")
    private BigDecimal Package_Amount;

    @JsonProperty("Time_Period_In_Days")
    private int Time_Period_In_Days;

    @JsonProperty("User_ID")
    private int User_ID;

    @JsonProperty("Paid")
    private boolean Paid;

    @JsonProperty("IsActive")
    private boolean IsActive;

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public String getService_Type() {
        return Service_Type;
    }

    public void setService_Type(String service_Type) {
        Service_Type = service_Type;
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

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
}
