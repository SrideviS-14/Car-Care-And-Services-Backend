package com.example.backend.controller;

import com.example.backend.model.PaymentDto;
import com.example.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/makePayment")
    public String makePayment(@RequestBody PaymentDto paymentDto) {
        paymentService.makePayment(paymentDto);
        return "Order placed Successfully";
    }

}
