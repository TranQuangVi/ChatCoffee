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

    @GetMapping("/deleteBrand")
    public String deleteBrand(){
        return "admin/managerAccounts/deleteBrand";
    }

    @GetMapping("/editBrand")
    public String editBrand(){
        return "admin/managerAccounts/editBrand";
    }

    @GetMapping("/addBrand")
    public String addBrand(){
        return "admin/managerAccounts/addBrand";
    }
}
