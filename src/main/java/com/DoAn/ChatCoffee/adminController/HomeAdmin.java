package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import com.DoAn.ChatCoffee.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeAdmin {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    ICTHoaDonRepository ctHoaDonRepository;
    @GetMapping
    public String index(Model model){

        model.addAttribute("slHD", hoaDonService.SoHD());
        model.addAttribute("slHDDangGiao", hoaDonService.SoHDDangGiao());
        model.addAttribute("slHDHoanThanh", hoaDonService.SoHDHT());
        return "admin/homeAdmin/index";
    }


}
