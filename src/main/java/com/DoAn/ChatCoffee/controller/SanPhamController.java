package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping
    public String product(Model model) {
        model.addAttribute("listProducts", sanPhamService.getAllProduct());
        return "sanpham/index";
    }

    /*giống như cái trên thôi - trả về trang /product*/
    @GetMapping("/danh-sach")
    public String index() {
        return "redirect:/sanpham";
    }

    @GetMapping("/tim-kiem")
    //todo: đưa vô 1 string --> list ()
    public String timkiem(@PathVariable String search, Model model){
        // gọi service tìm kiếm ()viết trong service
        //sanPhamService.getlistbySearchTring
        List<Sanpham> listProducts = sanPhamService.getListSearchProduct(search);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("search", search);
        return "sanpham/index";
    }
}
