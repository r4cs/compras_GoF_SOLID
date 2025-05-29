// src/main/java/com/br/ecommerce/external/fakestore/FakeStoreService.java
package com.br.ecommerce.external.fakestore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.ecommerce.domain.product.Product;
import com.br.ecommerce.dto.FakeStoreProductDTO;

import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreService {
    
    @Value("${fakestore.api.url}")
    private String apiUrl;
    
    private final RestTemplate restTemplate;
    private final FakeStoreProductAdapter adapter;
    
    public FakeStoreService(RestTemplate restTemplate, FakeStoreProductAdapter adapter) {
        this.restTemplate = restTemplate;
        this.adapter = adapter;
    }
    
    public List<Product> fetchAllProducts() {
        ResponseEntity<List<FakeStoreProductDTO>> response = restTemplate.exchange(
            apiUrl + "/products",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {}
        );
        
        return response.getBody().stream()
            .map(adapter::adapt)
            .toList();
    }
    
    public Optional<Product> fetchProductById(Long id) {
        try {
            FakeStoreProductDTO dto = restTemplate.getForObject(
                apiUrl + "/products/" + id,
                FakeStoreProductDTO.class
            );
            return Optional.ofNullable(adapter.adapt(dto));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}