package com.karma.grosery.ches.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_types")
public class ProductType implements Serializable {

    @Id
    @SequenceGenerator(name = "product_type_seq", sequenceName = "product_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_type_seq")
    private Long id;
    private String name;
    private String description;
    private Float utility;
    private Boolean arePieces;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "productTypeId",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"productType", "hibernateLazyInitializer", "handler"})
    private List<Product> products;

    public ProductType(){
        this.products = new ArrayList<>();
    }

    public ProductType(String name, String description, Float utility, Boolean arePieces) {
        this.name = name;
        this.description = description;
        this.utility = utility;
        this.arePieces = arePieces;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getUtility() {
        return utility;
    }

    public void setUtility(Float utility) {
        this.utility = utility;
    }

    public Boolean getArePieces() {
        return arePieces;
    }

    public void setArePieces(Boolean arePieces) {
        this.arePieces = arePieces;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
