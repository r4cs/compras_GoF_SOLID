package com.br.ecommerce.service;

import com.br.ecommerce.domain.*;
import com.br.ecommerce.domain.product.Product;
import com.br.ecommerce.dto.OrderRequestDTO;
import com.br.ecommerce.repository.OrderRepository;
import com.br.ecommerce.service.notification.NotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final ProductFacade productFacade;
    private final NotificationService notificationService;
    
    public OrderService(OrderRepository orderRepository,
                      ProductFacade productFacade,
                      NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.productFacade = productFacade;
        this.notificationService = notificationService;
    }
    
    public Order createOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setCustomer(request.getCustomer());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);
        
        // Padrão Facade para obter produtos
        request.getItems().forEach(item -> {
            Product product = productFacade.getProductById(item.getProductId())
                .orElseThrow(() -> new OrderException("Produto não encontrado: " + item.getProductId()));
            
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setUnitPrice(product.getPrice());
            
            order.addItem(orderItem);  // Agora usando o método correto
        });
        
        Order savedOrder = orderRepository.save(order);
        
        // Padrão Observer via NotificationService (Decorator)
        notificationService.send("Pedido criado: #" + savedOrder.getId());
        
        return savedOrder;
    }
    
    @Transactional
    public void advanceOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new OrderException("Pedido não encontrado: " + orderId));
        
        // Padrão State para transição de status
        if (!order.getStatus().canAdvance()) {
            throw new IllegalStateException("Não é possível avançar o status atual");
        }
        
        OrderStatus newStatus = order.getStatus().next();
        order.setStatus(newStatus);
        orderRepository.save(order);
        
        // Notificação com padrão Decorator
        notificationService.send("Status do pedido #" + orderId + " atualizado para: " + newStatus);
    }
    
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
    
    // Classe de exceção específica do domínio
    public static class OrderException extends RuntimeException {
        public OrderException(String message) {
            super(message);
        }
    }
}