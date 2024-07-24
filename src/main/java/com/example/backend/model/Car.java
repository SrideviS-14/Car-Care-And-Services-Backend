package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Car_Number")
    private int Car_Number;

    @Column(name="Car_Type")
    private String Car_Type;

    @Column(name="Car_Model")
    private String Car_Model;

    @Column(name="Car_Color")
    private String Car_Color;

    @Column(name="Car_Company")
    private String Car_Company;

    @Column(name="Id")
    private int UserID;

    public String getCar_Type() {
        return Car_Type;
    }

    public void setCar_Type(String car_Type) {
        Car_Type = car_Type;
    }

    public String getCar_Model() {
        return Car_Model;
    }

    public void setCar_Model(String car_Model) {
        Car_Model = car_Model;
    }

    public String getCar_Color() {
        return Car_Color;
    }

    public void setCar_Color(String car_Color) {
        Car_Color = car_Color;
    }

    public String getCar_Company() {
        return Car_Company;
    }

    public void setCar_Company(String car_Company) {
        Car_Company = car_Company;
    }

    public int getCar_Number() {
        return Car_Number;
    }

    public void setCar_Number(int car_Number) {
        Car_Number = car_Number;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
