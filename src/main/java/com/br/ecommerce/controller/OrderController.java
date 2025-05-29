// src/main/java/com/br/ecommerce/controller/OrderController.java
package com.br.ecommerce.controller;

import com.br.ecommerce.domain.Order;
import com.br.ecommerce.dto.OrderRequestDTO;
import com.br.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO request) {
        Order order = orderService.createOrder(request);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.ok(orders);
    }
    
    @PostMapping("/{id}/advance-status")
    public ResponseEntity<Void> advanceOrderStatus(@PathVariable Long id) {
        orderService.advanceOrderStatus(id);
        return ResponseEntity.ok().build();
    }
}

// // src/main/java/com/br/ecommerce/controller/OrderController.java
// package com.br.ecommerce.controller;

// import com.br.ecommerce.domain.Order;
// import com.br.ecommerce.dto.OrderRequestDTO;
// import com.br.ecommerce.service.OrderService;

// import java.util.List;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {
    
//     private final OrderService orderService;
    
//     public OrderController(OrderService orderService) {
//         this.orderService = orderService;
//     }
    
//     @PostMapping
//     public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO request) {
//         Order order = orderService.createOrder(request);
//         return ResponseEntity.ok(order);
//     }
    
//     @GetMapping("/{id}")
//     public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
//         return orderService.getOrderById(id)
//             .map(ResponseEntity::ok)
//             .orElse(ResponseEntity.notFound().build());
//     }
    
//     @GetMapping("/customer/{customerId}")
//     public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long customerId) {
//         List<Order> orders = orderService.getOrdersByCustomer(customerId);
//         return ResponseEntity.ok(orders);
//     }
// }