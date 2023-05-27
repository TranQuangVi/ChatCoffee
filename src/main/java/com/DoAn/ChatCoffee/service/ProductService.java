package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Cart;
import com.DoAn.ChatCoffee.entity.Product;
import com.DoAn.ChatCoffee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
    public Product getProductByID(Long product_id){
        Optional<Product> optional = productRepository.findById(product_id);
        return  optional.orElse(null);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProductByID(Long product_id){
        this.productRepository.deleteById(product_id);
    }


}
