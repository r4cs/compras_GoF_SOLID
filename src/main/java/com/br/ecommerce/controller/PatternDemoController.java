// src/main/java/com/br/ecommerce/controller/PatternDemoController.java
package com.br.ecommerce.controller;

import com.br.ecommerce.domain.payment.PaymentMethod;
import com.br.ecommerce.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patterns")
public class PatternDemoController {
    
    private final ShoppingCartService shoppingCartService;
    private final PaymentMethod creditCardPayment;
    private final PaymentMethod pixPayment;
    
    public PatternDemoController(ShoppingCartService shoppingCartService,
                               PaymentMethod creditCardPayment,
                               PaymentMethod pixPayment) {
        this.shoppingCartService = shoppingCartService;
        this.creditCardPayment = creditCardPayment;
        this.pixPayment = pixPayment;
    }
    
    @PostMapping("/strategy/checkout")
    public String checkout(@RequestParam String paymentType, @RequestParam double amount) {
        if ("credit".equals(paymentType)) {
            shoppingCartService.setPaymentMethod(creditCardPayment);
        } else if ("pix".equals(paymentType)) {
            shoppingCartService.setPaymentMethod(pixPayment);
        }
        
        shoppingCartService.checkout(amount);
        return "Checkout completed with " + paymentType;
    }
}