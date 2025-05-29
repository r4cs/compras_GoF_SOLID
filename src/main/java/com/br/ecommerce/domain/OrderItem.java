// src/main/java/com/br/ecommerce/domain/OrderItem.java
package com.br.ecommerce.domain;

import javax.persistence.*;

import com.br.ecommerce.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Product product;
    
    private int quantity;
    private double unitPrice;

}