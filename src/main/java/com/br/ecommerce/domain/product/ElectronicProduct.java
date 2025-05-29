// src/main/java/com/br/ecommerce/domain/product/ElectronicProduct.java
package com.br.ecommerce.domain.product;

import javax.persistence.Entity;

@Entity
public class ElectronicProduct extends Product {
    @Override
    public void displayDetails() {
        System.out.println("Eletrônico: " + getTitle() + " - R$" + getPrice());
    }
}