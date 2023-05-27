package com.DoAn.ChatCoffee.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/managerProducts")
public class ManagerProducts {
    @GetMapping
    public String test(){
        return "admin/managerProducts/test";
    }

    @GetMapping("/listProduct")
    public String listProduct(){
        return "admin/managerProducts/listProduct";
    }

    @GetMapping("/addProduct")
    public String addProduct(){
        return "admin/managerProducts/addProduct";
    }

    @GetMapping("/editProduct")
    public String editProduct(){
        return "admin/managerProducts/editProduct";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(){
        return "admin/managerProducts/deleteProduct";
    }
}
