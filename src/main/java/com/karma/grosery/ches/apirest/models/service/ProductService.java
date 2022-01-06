package com.karma.grosery.ches.apirest.models.service;

import com.karma.grosery.ches.apirest.models.entity.Product;
import com.karma.grosery.ches.apirest.models.entity.ProductType;
import com.karma.grosery.ches.apirest.repository.ProductRepository;
import com.karma.grosery.ches.apirest.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductTypeRepository productTypeRepository) {
        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Product product, Long productTypeId) {
        Optional<ProductType> productType = productTypeRepository.findProductTypeById(productTypeId);
        if(productType.isPresent()) {
            product.setProductTypeId(productType.get());
        }else{
            throw new IllegalStateException("El ID del tipo de producto: "+productTypeId+" no se encuentra registrado.");
        }
        Optional<Product> optionalProduct = productRepository.findProductByBarcode(product.getBarcode());
        if(optionalProduct.isPresent()){
            throw new IllegalStateException("El codigo de barras: "+product.getBarcode()+" ya esta registrado " +
                    "con el producto: "+optionalProduct.get().getName());
        }
        productRepository.save(product);
    }

    public void deleteProduct(Long productId) {
        if(!productRepository.existsById(productId)){
            throw new IllegalStateException("El id del producto: "+productId+" no existe.");
        }
        productRepository.deleteById(productId);
    }
    @Transactional
    public void updateProduct(Long productId, Long productTypeId, String barcode, String name,
                              String description, Float unitPrice, Float publicPrice) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            throw new IllegalStateException("El producto con el ID: "+productId+" no existe");
        }
        Product product = optionalProduct.get();
        if(productTypeId!=null && productTypeId>0 && product.getProductTypeId().getId()!=productTypeId){
            Optional<ProductType> optionalProductType = productTypeRepository.findById(productTypeId);
            if(optionalProductType.isPresent()){
                product.setProductTypeId(optionalProductType.get());
            }else{
                throw new IllegalStateException("El tipo de producto con el ID: "+productTypeId+" no existe.");
            }
        }
        if(barcode!=null && barcode.length()>0 && !Objects.equals(product.getBarcode(),barcode)){
            product.setBarcode(barcode);
        }
        if(name!=null && name.length()>0 && !Objects.equals(product.getName(),name)){
            product.setName(name);
        }
        if(description!=null && description.length()>0 && !Objects.equals(product.getDescription(),description)){
            product.setDescription(description);
        }
        if(unitPrice!=null && unitPrice>0 && product.getUnitPrice()!=unitPrice){
            product.setUnitPrice(unitPrice);
        }
        if(publicPrice!=null && publicPrice>0 && product.getPublicPrice()!=publicPrice){
            product.setPublicPrice(publicPrice);
        }
    }
}
