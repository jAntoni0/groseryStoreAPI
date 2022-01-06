package com.karma.grosery.ches.apirest.controllers;

import com.karma.grosery.ches.apirest.models.entity.Product;
import com.karma.grosery.ches.apirest.models.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "grosery/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping(path = "{productTypeId}")
    public void createProduct(@RequestBody Product product,
                              @PathVariable("productTypeId") Long productTypeId){
        productService.createProduct(product, productTypeId);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId){
        productService.deleteProduct(productId);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(@PathVariable("productId") Long productId,
                              @RequestParam(required = false) Long productTypeId,
                              @RequestParam(required = false) String barcode,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String description,
                              @RequestParam(required = false) Float unitPrice,
                              @RequestParam(required = false) Float publicPrice){
        productService.updateProduct(productId, productTypeId, barcode, name, description, unitPrice, publicPrice);
    }
}
