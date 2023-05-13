package com.DoAn.ChatCoffee.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/managerCategories")
public class ManagerCategories {
    @GetMapping
    public String index(){
        return "admin/managerCategory/index";
    }
}