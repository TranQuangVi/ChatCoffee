package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Cart;
import com.DoAn.ChatCoffee.entity.Product;
import com.DoAn.ChatCoffee.repository.ProductRepository;
import com.DoAn.ChatCoffee.service.ProductService;
import com.DoAn.ChatCoffee.service.ShoppingCartDetailService;
import com.DoAn.ChatCoffee.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ShoppingCarts")
public class ShoppingCartsController {
    @Autowired
    private ShoppingCartDetailService shoppingCartDetailService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @GetMapping
    public String index(Model model){
        model.addAttribute("productInCarts", shoppingCartDetailService.getAll());
        return "ShoppingCart/index";
    }

    @GetMapping("/addToCart/{id}")
    public  String addBookForm(@PathVariable Long id){
        Product product = productService.getProductByID(id);
        Cart cart = shoppingCartService.getCartByID(1L);
        shoppingCartDetailService.addToCart(product,cart);
        return "redirect:/ShoppingCarts";
    }


}
