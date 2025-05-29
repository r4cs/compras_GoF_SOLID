// src/main/java/com/br/ecommerce/service/ProductFacade.java
package com.br.ecommerce.service;

import com.br.ecommerce.domain.product.Product;
import com.br.ecommerce.external.fakestore.FakeStoreProductAdapter;
import com.br.ecommerce.external.fakestore.FakeStoreService;
import com.br.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductFacade {
    
    private final FakeStoreService fakeStoreService;
    private final ProductRepository productRepository;
    private final FakeStoreProductAdapter adapter;
    
    public ProductFacade(FakeStoreService fakeStoreService, 
                        ProductRepository productRepository,
                        FakeStoreProductAdapter adapter) {
        this.fakeStoreService = fakeStoreService;
        this.productRepository = productRepository;
        this.adapter = adapter;
    }
    
    public List<Product> getAllProducts() {
        // Primeiro tenta buscar da API externa
        List<Product> externalProducts = fakeStoreService.fetchAllProducts();
        
        if(externalProducts.isEmpty()) {
            // Fallback para o banco de dados local
            return productRepository.findAll();
        }
        
        return externalProducts;
    }
    
    public Optional<Product> getProductById(Long id) {
        // Tenta buscar da API externa primeiro
        Optional<Product> externalProduct = fakeStoreService.fetchProductById(id);
        
        if(externalProduct.isPresent()) {
            return externalProduct;
        }
        
        // Fallback para o banco de dados local
        return productRepository.findById(id);
    }
    
    // Outros métodos da fachada...
}