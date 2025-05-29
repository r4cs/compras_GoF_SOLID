// src/main/java/com/br/ecommerce/dto/OrderItemDTO.java
package com.br.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long productId;
    private int quantity;

    // Getters e Setters
    // public Long getProductId() {
    //     return productId;
    // }

    // public void setProductId(Long productId) {
    //     this.productId = productId;
    // }

    // public int getQuantity() {
    //     return quantity;
    // }

    // public void setQuantity(int quantity) {
    //     this.quantity = quantity;
    // }
}