// src/main/java/com/br/ecommerce/service/factory/ElectronicProductFactory.java
package com.br.ecommerce.service.factory;

import com.br.ecommerce.domain.product.ElectronicProduct;
import com.br.ecommerce.domain.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ElectronicProductFactory implements ProductFactory {
    @Override
    public Product createProduct(String title, double price) {
        ElectronicProduct product = new ElectronicProduct();
        product.setTitle(title);
        product.setPrice(price);
        product.setCategory("Electronics");
        return product;
    }
}