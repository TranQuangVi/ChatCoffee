package com.DoAn.ChatCoffee.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/managerAccounts")
public class ManagerAccounts {
    @GetMapping
    public String index(){
        return "admin/managerAccounts/index";
    }
}
