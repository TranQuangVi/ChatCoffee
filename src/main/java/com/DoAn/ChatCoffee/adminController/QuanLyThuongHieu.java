package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Thuonghieu;
import com.DoAn.ChatCoffee.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/QuanLyThuongHieu")
public class QuanLyThuongHieu {
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("ListBrands",thuongHieuService.getAllThuongHieu());
        return "admin/QuanLyThuongHieu/index";
    }

    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable("id") Long id){
        Thuonghieu thuonghieu= thuongHieuService.getThuongHieuById(id);
        thuongHieuService.deleteThuongHieu(thuonghieu.getMaTH());
        return "redirect:/admin/QuanLyThuongHieu";
    }

    @GetMapping("/editBrand/{id}")
    public String editBrandForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("brand",thuongHieuService.getThuongHieuById(id));
        return "admin/QuanLyThuongHieu/editBrand";
    }
    @PostMapping("/editBrand")
    public String editBrand(@ModelAttribute("brand") Thuonghieu updatebrand){
        thuongHieuService.saveThuongHieu(updatebrand);
        return "redirect:/admin/QuanLyThuongHieu";
    }

    @GetMapping("/addBrand")
    public String addBrandFrom(Model model){
        model.addAttribute("brand", new Thuonghieu());
        return "admin/QuanLyThuongHieu/addBrand";
    }
    @PostMapping("/addBrand")
    public String addBrand(@ModelAttribute("brand") Thuonghieu thuonghieu){
        thuongHieuService.saveThuongHieu(thuonghieu);
        return "redirect:/admin/QuanLyThuongHieu";
    }
}
