
// src/main/java/com/br/ecommerce/domain/product/Product.java
package com.br.ecommerce.domain.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @Id
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
    
    public abstract void displayDetails();
}