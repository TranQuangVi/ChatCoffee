package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Cart;
import com.DoAn.ChatCoffee.repository.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartService {
    @Autowired
    private IShoppingCartRepository shoppingCartRepository;
    public Cart getCartByID(Long cart_id){
        Optional<Cart> optional = shoppingCartRepository.findById(cart_id);
        return  optional.orElse(null);
    }
}
