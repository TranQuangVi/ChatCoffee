package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.service.CTHoaDonService;
import com.DoAn.ChatCoffee.service.HoaDonService;
import com.DoAn.ChatCoffee.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/QuanLyHoaDon")
public class QuanLyHoaDon {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private CTHoaDonService ctHoaDonService;



    @GetMapping("/cho-lay-hang")
    public String index(Model model ){
        model.addAttribute("quanlyhoadon", hoaDonService.gethoadonchoduyet());
        model.addAttribute("chitiethoadon",ctHoaDonService.getAllCTHoaDon());
        return "admin/QuanLyHoaDon/index";
    }

    @GetMapping("/dang-giao")
    public String danggiaohoadon(Model model){
        model.addAttribute("quanlyhoadon", hoaDonService.gethoadondanggiao());
        model.addAttribute("chitiethoadon2",ctHoaDonService.getAllCTHoaDon());
        return "admin/QuanLyHoaDon/index";
    }
    @GetMapping("/da-hoan-thanh")
    public String dahoanthanhhoadon(Model model, String usrname){
        model.addAttribute("quanlyhoadon",hoaDonService.gethoadondahoanthanh());
        model.addAttribute("chitiethoadon3",ctHoaDonService.getAllCTHoaDon());
        return "admin/QuanLyHoaDon/index";
    }

}
