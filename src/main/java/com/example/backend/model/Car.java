package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name="Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int car_ID;

    @Column(name = "car_Number")
    private String car_Number;

    @Column(name="car_Type")
    private String car_Type;

    @Column(name="car_Model")
    private String car_Model;

    @Column(name="car_Color")
    private String car_Color;

    @Column(name="car_Company")
    private String car_Company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private AppUser appUser;

    public int getCar_ID() {
        return car_ID;
    }

    public void setCar_ID(int Car_ID) {
        car_ID = Car_ID;
    }

    public String getCar_Type() {
        return car_Type;
    }

    public void setCar_Type(String Car_Type) {
        car_Type = Car_Type;
    }

    public String getCar_Model() {
        return car_Model;
    }

    public void setCar_Model(String Car_Model) {
        car_Model = Car_Model;
    }

    public String getCar_Color() {
        return car_Color;
    }

    public void setCar_Color(String Car_Color) {
        car_Color = Car_Color;
    }

    public String getCar_Company() {
        return car_Company;
    }

    public void setCar_Company(String Car_Company) {
        car_Company = Car_Company;
    }

    public String getCar_Number() {
        return car_Number;
    }

    public void setCar_Number(String Car_Number) {
        car_Number = Car_Number;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
