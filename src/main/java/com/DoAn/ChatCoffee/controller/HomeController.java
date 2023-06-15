package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Loaisanpham;
import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Thuonghieu;
import com.DoAn.ChatCoffee.service.LoaiSanPhamService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private SanPhamService sanPhamService;

    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
     private  ThuongHieuService thuongHieuService;

    @GetMapping
    public String homePage(Model model){
        // Hiển thị Menu tất cả sản phẩm nổi bật (sanpham ==8)
        List<Sanpham>  sanphams = sanPhamService.getAllProduct();
        Collections.shuffle(sanphams);
        int sanpham = 8;
        if(sanphams.size() > sanpham){
            sanphams = sanphams.subList(0,sanpham);
        }
        model.addAttribute("listProducts", sanphams);

        //Hiển thị Menu tất cả loại (loai == 6)
        List<Loaisanpham> loaisanphams = loaiSanPhamService.getAllCategories();
        Collections.shuffle(loaisanphams);  // Xáo trộn danh sách loại sản phẩm ngẫu nhiên
        int sosanphamhienthi = 6; // Số lượng loại sản phẩm muốn hiển thị
        if (loaisanphams.size() > sosanphamhienthi) { // Kiểm tra số loại sản phẩm và chỉ lấy số lượng mong muốn
            loaisanphams = loaisanphams.subList(0, sosanphamhienthi);
        }
              model.addAttribute("listLoais", loaisanphams);

        //Hiện menu thương hiệu tối đa ( 4 thương hiệu)
        List<Thuonghieu> th = thuongHieuService.getAllThuongHieu();
        Collections.shuffle(th);
        int thuonghieu = 4;
        if(th.size() > thuonghieu){
           th = th.subList(0,thuonghieu);
        }
       model.addAttribute("danhsachthuonghieu", th);
        return "home/index";
    }
    //
    @GetMapping("/loai/{id}")
    public  String Locloai(@PathVariable Long id, Model model ){
        model.addAttribute("listProducts", sanPhamService.getSanPhamByIdloai(id));
        model.addAttribute("danhsachloai",loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        return  "sanpham/index";
    }


    // lọc theo thương hiệu
    @GetMapping("/thuonghieu/{id}")
    public String  Locthuonghieu(@PathVariable Long id  , Model model) {
        model.addAttribute("listProducts", sanPhamService.getSanPhamByIdThuonghieu(id));
        model.addAttribute("danhsachloai",loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        return "sanpham/index";
    }
    @GetMapping("/chi-tiet/{id}")
    public String chiTietSP(@PathVariable Long id, Model model){
        model.addAttribute("sanpham", sanPhamService.getProductByID(id));
        return "sanpham/chitietsp";
    }

}
