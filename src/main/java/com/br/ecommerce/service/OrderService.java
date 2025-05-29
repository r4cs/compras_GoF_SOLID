// // src/main/java/com/br/ecommerce/service/OrderService.java
// package com.br.ecommerce.service;

// import com.br.ecommerce.domain.*;
// import com.br.ecommerce.domain.product.Product;
// import com.br.ecommerce.dto.OrderRequestDTO;
// import com.br.ecommerce.repository.OrderRepository;
// import com.br.ecommerce.repository.ProductRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class OrderService {
    
//     private final OrderRepository orderRepository;
//     private final ProductRepository productRepository;
    
//     public OrderService(OrderRepository orderRepository, 
//                        ProductRepository productRepository) {
//         this.orderRepository = orderRepository;
//         this.productRepository = productRepository;
//     }
    
//     @Transactional
//     public Order createOrder(OrderRequestDTO request) {
//         Order order = new Order();
//         order.setCustomer(request.getCustomer());
//         order.setOrderDate(LocalDateTime.now());
//         order.setStatus(OrderStatus.NEW);
//         order.setState(new NewOrderState());
        
//         request.getItems().forEach(itemRequest -> {
//             Product product = productRepository.findById(itemRequest.getProductId())
//                 .orElseThrow(() -> new RuntimeException("Product not found"));
            
//             OrderItem item = new OrderItem();
//             item.setProduct(product);
//             item.setQuantity(itemRequest.getQuantity());
//             item.setUnitPrice(product.getPrice());
//             order.getItems().add(item);
//         });
        
//         return orderRepository.save(order);
//     }
    
//     @Transactional
//     public void advanceOrderStatus(Long orderId) {
//         Order order = orderRepository.findById(orderId)
//             .orElseThrow(() -> new RuntimeException("Order not found"));
        
//         order.nextState();
//         order.setStatus(mapStateToStatus(order.getState()));
//         orderRepository.save(order);
//     }
    
//     private OrderStatus mapStateToStatus(OrderState state) {
//         if (state instanceof NewOrderState) {
//             return OrderStatus.NEW;
//         } else if (state instanceof ProcessingOrderState) {
//             return OrderStatus.PROCESSING;
//         } else if (state instanceof ShippedOrderState) {
//             return OrderStatus.SHIPPED;
//         } else if (state instanceof DeliveredOrderState) {
//             return OrderStatus.DELIVERED;
//         }
//         return OrderStatus.CANCELLED;
//     }
    
//     public Optional<Order> getOrderById(Long id) {
//         return orderRepository.findById(id);
//     }
    
//     public List<Order> getOrdersByCustomer(Long customerId) {
//         return orderRepository.findByCustomerId(customerId);
//     }
// }

// src/main/java/com/br/ecommerce/service/OrderService.java
package com.br.ecommerce.service;

import com.br.ecommerce.domain.*;
import com.br.ecommerce.domain.product.Product;
import com.br.ecommerce.dto.OrderRequestDTO;
import com.br.ecommerce.repository.OrderRepository;
import com.br.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    
    public OrderService(OrderRepository orderRepository, 
                       ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    
    @Transactional
    public Order createOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setCustomer(request.getCustomer());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);
        
        List<OrderItem> items = request.getItems().stream()
            .map(itemRequest -> {
                Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
                
                OrderItem item = new OrderItem();
                item.setProduct(product);
                item.setQuantity(itemRequest.getQuantity());
                item.setUnitPrice(product.getPrice());
                return item;
            })
            .toList();
        
        order.setItems(items);
        return orderRepository.save(order);
    }
    
    @Transactional
    public void advanceOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found"));
        
        OrderStatus currentStatus = order.getStatus();
        OrderStatus newStatus = currentStatus.next();
        
        if (newStatus == null) {
            throw new IllegalStateException("Cannot advance from current status");
        }
        
        order.setStatus(newStatus);
        orderRepository.save(order);
    }
    
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
    
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}