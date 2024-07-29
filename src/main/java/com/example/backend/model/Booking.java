package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name="Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Booking_ID;

    @Column(name="Service_List")
    private String Service_List;

    @Column(name="Package_Amount")
    private BigDecimal Package_Amount;

    @Column(name="Time_Period_In_Days")
    private int Time_Period_In_Days;

    @Column(name="Id")
    private int User_ID;

    @Column(name="Paid")
    private boolean Paid;

    @Column(name="DateOfBooking")
    private Date DateOfBooking;

    @Column(name="Status")
    private String Status;

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
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
    public int getBooking_ID() {
        return Booking_ID;
    }

    public void setBooking_ID(int booking_ID) {
        Booking_ID = booking_ID;
    }


}
