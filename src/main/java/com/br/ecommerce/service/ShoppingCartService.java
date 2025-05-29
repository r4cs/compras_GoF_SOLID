// src/main/java/com/br/ecommerce/service/ShoppingCartService.java
package com.br.ecommerce.service;

import com.br.ecommerce.domain.payment.PaymentMethod;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    private PaymentMethod paymentMethod;
    
    public void setPaymentMethod(PaymentMethod method) {
        this.paymentMethod = method;
    }
    
    public void checkout(double total) {
        paymentMethod.pay(total);
    }
}