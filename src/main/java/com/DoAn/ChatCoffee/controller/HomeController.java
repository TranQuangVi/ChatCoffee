package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.service.LoaiSanPhamService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @GetMapping
    public String homePage(Model model){

        model.addAttribute("listProducts", sanPhamService.getAllProduct());

        model.addAttribute("listLoais", loaiSanPhamService.getAllCategories());


        return "home/index";
    }
}
