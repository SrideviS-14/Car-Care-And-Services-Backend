package com.example.backend.model;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Services")
public class Services
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Service_ID;

    @Column(name="Service_Name")
    private String Service_Name;

    @Column(name="Service_Amount")
    private BigDecimal Service_Amount;

    @Column(name="Description")
    private String Description;

    public int getService_ID() {
        return Service_ID;
    }

    public void setService_ID(int service_ID) {
        Service_ID = service_ID;
    }

    public String getService_Name() {
        return Service_Name;
    }

    public void setService_Name(String service_Name) {
        Service_Name = service_Name;
    }

    public BigDecimal getService_Amount() {
        return Service_Amount;
    }

    public void setService_Amount(BigDecimal service_Amount) {
        Service_Amount = service_Amount;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
