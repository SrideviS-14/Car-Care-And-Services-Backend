package com.example.backend.model;


import jakarta.persistence.*;

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
    private double Service_Amount;
}
