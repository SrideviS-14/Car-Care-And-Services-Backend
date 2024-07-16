package com.example.backend.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDto {
    @NotEmpty
    private String userName;

    @NotEmpty
    private String email;

    private String phoneNumber;

    @NotEmpty
    @Size(min = 8, message = "Minimum Password Length is 8 Characters")
    private String password;

    public @NotEmpty String getUserName() {
        return userName;
    }

    public void setUserName(@NotEmpty String userName) {
        this.userName = userName;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public @NotEmpty @Size(min = 8, message = "Minimum Password Length is 8 Characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 8, message = "Minimum Password Length is 8 Characters") String password) {
        this.password = password;
    }
}
