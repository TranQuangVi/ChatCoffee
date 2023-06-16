package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.service.LoaiSanPhamService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
       /* model.addAttribute("dsSanphams", sanPhamService.getSearchListProduct(search));
        model.addAttribute("search",search);*/
        return pageproduct(model,search,1);

    }
    @GetMapping("/page/{pageNo}")
    public String pageproduct(Model model, String search, @PathVariable(value = "pageNo") int pageNo ) {
        int pageSize= 6;
        Page<Sanpham> page=sanPhamService.findPaginated(pageNo, pageSize);/////////////////////
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        List<Sanpham> listProducts= page.getContent();
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("danhsachloai",loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        model.addAttribute("search",search);
        return "sanpham/index";
    }


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

}
