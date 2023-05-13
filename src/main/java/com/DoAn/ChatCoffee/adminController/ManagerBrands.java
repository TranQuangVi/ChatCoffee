package com.DoAn.ChatCoffee.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/managerBrands")
public class ManagerBrands {
    @GetMapping
    public String index(){
        return "admin/managerBrands/index";
    }
}
