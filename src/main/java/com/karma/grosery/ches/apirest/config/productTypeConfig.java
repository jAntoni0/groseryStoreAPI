package com.karma.grosery.ches.apirest.config;

import com.karma.grosery.ches.apirest.models.entity.ProductType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class productTypeConfig {

   /* @Bean
    CommandLineRunner commandLineRunner (){
        return args ->{
            List<ProductType> productTypes = new ArrayList<>();

            productTypes.add(new ProductType("Refrescos Coca-Cola","Solo familia Coca Cola", 12.0f,true,));

        };
    }*/
}
