package com.karma.grosery.ches.apirest.repository;

import com.karma.grosery.ches.apirest.models.entity.Product;
import com.karma.grosery.ches.apirest.models.entity.ProductType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByBarcode(String barcode);
}
