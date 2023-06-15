package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.Giohang;
import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.service.CTHoaDonService;
import com.DoAn.ChatCoffee.service.CTGioHangService;
import com.DoAn.ChatCoffee.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/hoa-don")
public class HoaDonController {
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    private CTGioHangService CTGioHangService;
    @Autowired
    CTHoaDonService ctHoaDonService;
    @GetMapping
    public String themHoaDon(Authentication authentication, Model model){
        model.addAttribute("ctGiohangs", CTGioHangService.getGioHangByUserName(authentication.getName()));
    //    model.addAttribute("ctGiohangs", ctGiohangs);
        model.addAttribute("hoadon", new Hoadon());
        return "giohang/hoadon";
    }
    @PostMapping("/mua")
    public String mua(@ModelAttribute("ctGiohangs" )List<CTGiohang> ctGiohangs, @ModelAttribute("hoadon" )Hoadon hoadon){
        hoaDonService.save(hoadon);
        ctHoaDonService.save(ctGiohangs,hoadon);
        return "redirect:/user/hoa-don";
    }

}
