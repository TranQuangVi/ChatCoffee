package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.service.LoaiSanPhamService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
  private SanPhamService sanPhamService;



    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;




    @GetMapping
    public String product(Model model, @Param("search") String search) {
        model.addAttribute("listProducts", sanPhamService.getSearchListProduct(search));
//        model.addAttribute("listProducts", sanPhamService.getAllProduct());

        model.addAttribute("danhsachloai",loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        model.addAttribute("search",search);
        return "sanpham/index";
    }

    /*giống như cái trên thôi - trả về trang /product*/
    @GetMapping("/danh-sach")
    public String index() {
        return "redirect:/sanpham";
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

    // Lọc sản phẩm theo  giá
    @GetMapping("/gia/{gia}")
    public  String loctheogia( @PathVariable Long gia,  Model model){
        model.addAttribute("listProducts", sanPhamService.getSanphamtheoGia(gia));
        model.addAttribute("danhsachloai",loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        model.addAttribute("danhsachgia", sanPhamService.getAllProduct());
        return "sanpham/index";
    }
    @GetMapping("/tensp/{tensp}")
    public  String loctheotensanpham(@PathVariable String tensp,  Model model) {
        model.addAttribute("listProducts", sanPhamService.getSanphamtheotensp(tensp));
        model.addAttribute("danhsachloai", loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        model.addAttribute("danhsachloctensanpham", sanPhamService.getAllProduct());
        return "sanpham/index";
    }
    @GetMapping("/chi-tiet/{id}")
    public String chiTietSP(@PathVariable Long id, Model model){
        model.addAttribute("sanpham", sanPhamService.getProductByID(id));
        return "sanpham/chitietsp";
    }
    @GetMapping("/tim-kiem")
    //todo: đưa vô 1 string --> list ()
    public String timkiem(String bien){
        // gọi service tìm kiếm ()viết trong service
        //sanPhamService.getlistbySearchTring
       // model.addAttribute("listProducts", sanPhamService.getAllProduct());
        return "sanpham/index";
    }
}
