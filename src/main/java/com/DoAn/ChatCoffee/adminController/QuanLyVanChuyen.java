package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.service.QuanLyVanChuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/vanchuyen")
public class QuanLyVanChuyen {
    @Autowired
    private QuanLyVanChuyenService quanLyVanChuyenService;

    @GetMapping()
    public String getAllVanChuyen(Model model){
        model.addAttribute("listvc", quanLyVanChuyenService.getAllVanChuyen());
        return "admin/quanlyvanchuyen/index";
    }
}
