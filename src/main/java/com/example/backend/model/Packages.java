package com.example.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name="Packages")
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Package_ID;

    @Column(name="Package_Name")
    private String Package_Name;

    @Column(name="Package_Amount")
    private BigDecimal Package_Amount;

    @Column(name="Service_List")
    private String Service_List;

    @Column(name="Time_Period_In_Days")
    private Integer Time_Period_In_Days;

    public int getPackage_ID() {
        return Package_ID;
    }

    public void setPackage_ID(int package_ID) {
        Package_ID = package_ID;
    }

    public String getPackage_Name() {
        return Package_Name;
    }

    public void setPackage_Name(String package_Name) {
        Package_Name = package_Name;
    }

    public BigDecimal getPackage_Amount() {
        return Package_Amount;
    }

    public void setPackage_Amount(BigDecimal package_Amount) {
        Package_Amount = package_Amount;
    }

    public String getService_List() {
        return Service_List;
    }

    public void setService_List(String service_List) {
        Service_List = service_List;
    }

    public Integer getTime_Period_In_Days() {
        return Time_Period_In_Days;
    }

    public void setTime_Period_In_Days(Integer time_Period_In_Days) {
        Time_Period_In_Days = time_Period_In_Days;
    }
}
