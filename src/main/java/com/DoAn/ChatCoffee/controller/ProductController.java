package com.DoAn.ChatCoffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping
    public String product(){
        return "product/index";
    }
    /*giống như cái trên thôi - trả về trang /product*/
    @GetMapping("/index")
    public String index(){
        return "redirect:/product";
    }
}
