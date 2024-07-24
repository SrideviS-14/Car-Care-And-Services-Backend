package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDto {

    @JsonProperty("Car_Number")
    private String Car_Number;

    @JsonProperty("Car_Type")
    private String Car_Type;

    @JsonProperty("Car_Model")
    private String Car_Model;

    @JsonProperty("Car_Color")
    private String Car_Color;

    @JsonProperty("Car_Company")
    private String Car_Company;

    @JsonProperty("Id")
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCar_Number() {
        return Car_Number;
    }

    public void setCar_Number(String car_Number) {
        Car_Number = car_Number;
    }

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
}
