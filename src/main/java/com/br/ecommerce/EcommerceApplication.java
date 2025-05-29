package com.br.ecommerce;

import com.br.ecommerce.domain.catalog.CategoryComposite;
import com.br.ecommerce.domain.catalog.ProductLeaf;
import com.br.ecommerce.repository.CatalogComponentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CatalogComponentRepository repository) {
        return args -> {
            // Criando uma categoria com produtos
            CategoryComposite electronics = new CategoryComposite();
            electronics.setName("Eletrônicos");
            
            ProductLeaf tv = new ProductLeaf();
            tv.setName("TV Smart 55\"");
            
            ProductLeaf smartphone = new ProductLeaf();
            smartphone.setName("Smartphone Premium");
            
            electronics.getComponents().add(tv);
            electronics.getComponents().add(smartphone);
            
            repository.save(electronics);
            
            System.out.println("Catálogo salvo com sucesso!");
        };
    }
}
// // src/main/java/com/br/ecommerce/EcommerceApplication.java
// package com.br.ecommerce;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class EcommerceApplication {
//     public static void main(String[] args) {
//         SpringApplication.run(EcommerceApplication.class, args);
//     }
// }