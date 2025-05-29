// src/main/java/com/br/ecommerce/dto/OrderRequestDTO.java
package com.br.ecommerce.dto;

import com.br.ecommerce.domain.Customer;
import java.util.List;

public class OrderRequestDTO {
    private Customer customer;
    private List<OrderItemDTO> items;

    // Getters e Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}

// // src/main/java/com/br/ecommerce/dto/OrderRequestDTO.java
// package com.br.ecommerce.dto;

// import com.br.ecommerce.domain.Customer;

// import lombok.Getter;
// import lombok.Setter;

// import java.util.List;

// @Getter
// @Setter
// public class OrderRequestDTO {
//     private Customer customer;
//     private List<OrderItemDTO> items;

//     // Getters e Setters
//     // public Customer getCustomer() {
//     //     return customer;
//     // }

//     // public void setCustomer(Customer customer) {
//     //     this.customer = customer;
//     // }

//     // public List<OrderItemDTO> getItems() {
//     //     return items;
//     // }

//     // public void setItems(List<OrderItemDTO> items) {
//     //     this.items = items;
//     // }
// }