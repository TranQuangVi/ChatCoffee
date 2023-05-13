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
}
