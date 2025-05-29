package com.br.ecommerce.domain.catalog;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CategoryComposite extends CatalogComponent {
    
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    private List<CatalogComponent> components = new ArrayList<>();
    
    @Override
    public void display() {
        System.out.println("Categoria: " + name);
        for (CatalogComponent component : components) {
            component.display();
        }
    }
    
    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CatalogComponent> getComponents() {
        return components;
    }

    public void setComponents(List<CatalogComponent> components) {
        this.components = components;
    }
}