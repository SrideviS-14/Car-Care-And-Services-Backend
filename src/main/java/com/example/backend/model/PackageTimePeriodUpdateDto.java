package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageTimePeriodUpdateDto {

    @JsonProperty("PackageID")
    private int PackageID;

    @JsonProperty("Time_Period_In_Days")
    private int Time_Period_In_Days;

    public int getPackageID() {
        return PackageID;
    }

    public void setPackageID(int packageID) {
        this.PackageID = packageID;
    }

    public int getTime_Period_In_Days() {
        return Time_Period_In_Days;
    }

    public void setTime_Period_In_Days(int time_Period_In_Days) {
        Time_Period_In_Days = time_Period_In_Days;
    }
}
