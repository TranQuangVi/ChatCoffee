package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Cart;
import com.DoAn.ChatCoffee.entity.CartDetails;
import com.DoAn.ChatCoffee.entity.CartDetailsKey;
import com.DoAn.ChatCoffee.entity.Product;
import com.DoAn.ChatCoffee.repository.IShoppingCartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartDetailService {
    @Autowired
     IShoppingCartDetailRepository shoppingCartDetailRepository;
    public List<CartDetails> getAll(){
        return shoppingCartDetailRepository.findAll();
    }

    public CartDetails getItemByID(CartDetailsKey cartDetailsKey){
        Optional<CartDetails> optional = shoppingCartDetailRepository.findById(cartDetailsKey);
        return  optional.orElse(null);
    }

    public void addToCart(Product product, Cart cart){

        CartDetailsKey cartDetailsKey= new CartDetailsKey();
        cartDetailsKey.setProduct_id(product.getProduct_id());
        cartDetailsKey.setCart_id(cart.getCart_id());

        Optional<CartDetails> optional = shoppingCartDetailRepository.findById(cartDetailsKey);
        CartDetails cartDetails= new CartDetails();
        if(optional.isPresent()){
            cartDetails=optional.get();
            cartDetails.setSoLuong(cartDetails.getSoLuong() +1);
        }
        else {
            cartDetails.setProduct(product);
            cartDetails.setCart(cart);
            cartDetails.setSoLuong(1);
        }
        cartDetails.setId(cartDetailsKey);
        shoppingCartDetailRepository.save(cartDetails);
    }
}
