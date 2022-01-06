package com.karma.grosery.ches.apirest.models.service;

import com.karma.grosery.ches.apirest.models.entity.ProductType;
import com.karma.grosery.ches.apirest.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getProductTypes() {
        return productTypeRepository.findAll();
    }

    public void createProductType(ProductType productType) {
        productTypeRepository.save(productType);
    }

    public void deleteProductType(Long productTypeId){
        if(!productTypeRepository.existsById(productTypeId)){
            throw new IllegalStateException("El tipo de producto con el ID: "+productTypeId+" no existe.");
        }
        productTypeRepository.deleteById(productTypeId);
    }
    @Transactional
    public void updateProductType(Long productTypeId, String name, String description, Float utility, Boolean arePieces) {
        Optional<ProductType> productTypeOptional = productTypeRepository.findById(productTypeId);
        if(!productTypeOptional.isPresent()){
            throw new IllegalStateException("El tipo de producto con ID: "+productTypeId+" no existe.");
        }
        ProductType productType = productTypeOptional.get();
        if(name != null && name.length()>0 && !Objects.equals(productType.getName(),name)){
            productType.setName(name);
        }
        if(description != null && description.length()>0 && !Objects.equals(productType.getDescription(),description)){
            productType.setDescription(description);
        }
        if(utility != null && utility>0 && utility!=productType.getUtility()){
            productType.setUtility(utility);
        }
        if(arePieces != null && arePieces!=productType.getArePieces()){
            productType.setArePieces(arePieces);
        }
    }

    public Optional<ProductType> findProductTypeById(Long productTypeId) {
        return productTypeRepository.findById(productTypeId);
    }
}
