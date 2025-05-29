package com.br.ecommerce.config;

import com.br.ecommerce.service.notification.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NotificationConfig {
    
    @Bean
    @Primary // marca este bean como a implementação principal
        public NotificationService notificationChain() {
        // Crie as instâncias manualmente sem injeção do Spring
        NotificationService base = new BaseNotificationService();
        NotificationService email = new EmailNotificationService(base);
        NotificationService sms = new SmsNotificationService(email);
        
        return sms; // Retorna a cadeia completa
    }
    // public NotificationService notificationChain(
    //         BaseNotificationService baseService,
    //         EmailNotificationService emailService,
    //         SmsNotificationService smsService) {
        
    //     // Construindo a cadeia manualmente através dos construtores
    //     NotificationService chain = baseService;
    //     chain = new EmailNotificationService(chain);
    //     chain = new SmsNotificationService(chain);
        
    //     return chain;
    // }
}