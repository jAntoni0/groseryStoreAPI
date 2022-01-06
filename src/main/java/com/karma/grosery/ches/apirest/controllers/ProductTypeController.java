package com.karma.grosery.ches.apirest.controllers;

import com.karma.grosery.ches.apirest.models.entity.ProductType;
import com.karma.grosery.ches.apirest.models.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "grosery/v1/product-type")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public List<ProductType> getProductTypes(){
        return productTypeService.getProductTypes();
    }

    @PostMapping
    public void createNewProductType(@RequestBody ProductType productType){
        productTypeService.createProductType(productType);
    }

    @DeleteMapping(path = "{productTypeId}")
    public void deleteProductType(@PathVariable("productTypeId") Long productTypeId){
        productTypeService.deleteProductType(productTypeId);
    }

    @PutMapping(path = "{productTypeId}")
    public void updateProductType(@PathVariable("productTypeId") Long productTypeId,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String description,
                                  @RequestParam(required = false) Float utility,
                                  @RequestParam(required = false) Boolean arePieces){
        productTypeService.updateProductType(productTypeId,name,description,utility,arePieces);
    }
}
