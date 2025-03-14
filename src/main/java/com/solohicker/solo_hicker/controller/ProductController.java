package com.solohicker.solo_hicker.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private record Product(Integer productId,
                           String productName,
                           double productPrice){}

    List<Product> products = new ArrayList<>(
            List.of(
                    new Product(1,"iPhone",999.99),
                    new Product(2,"Mac Pro",9999.99)
            )
    );

    @GetMapping("/get-product")
    public List<Product> getProducts(){
        return products;
    }

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product){
        products.add(product);
        return product;
    }
}
