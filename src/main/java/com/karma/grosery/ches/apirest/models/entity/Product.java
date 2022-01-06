package com.karma.grosery.ches.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", nullable = false, updatable = false)
    @JsonIgnoreProperties({"products","hibernateLazyInitializer", "handler"})
    private ProductType productTypeId;
    private String barcode;
    private String name;
    private String description;
    private Float unitPrice;
    private Float publicPrice;
    @OneToOne(mappedBy = "productId")
    private Inventory inventory;

    public Product() {
    }

    public Product(ProductType productTypeId, String name, String description, Float unitPrice, Float publicPrice) {
        this.productTypeId = productTypeId;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.publicPrice = publicPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(ProductType productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Float getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Float publicPrice) {
        this.publicPrice = publicPrice;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productTypeId=" + productTypeId +
                ", barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", publicPrice=" + publicPrice +
                '}';
    }

}
