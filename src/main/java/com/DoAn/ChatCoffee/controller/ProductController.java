package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String product(Model model) {
        model.addAttribute("listProducts", productService.getAllProduct());
        return "product/index";
    }

    /*giống như cái trên thôi - trả về trang /product*/
    @GetMapping("/list")
    public String index() {
        return "redirect:/product";
    }


}
