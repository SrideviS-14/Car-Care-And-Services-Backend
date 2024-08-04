package com.example.backend.model;

import java.math.BigDecimal;

public class CartDto {

    private Services services;
    private boolean isCustom;
    private BigDecimal total;

    public CartDto(Services service, boolean isCustom, BigDecimal total) {
        this.services = service;
        this.isCustom = isCustom;
        this.total = total;
    }
    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
