package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Giohang;
import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.CTGioHangService;
import com.DoAn.ChatCoffee.service.GioHangService;
import com.DoAn.ChatCoffee.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.Long.parseLong;

@Controller
@RequestMapping("/gio-hang")
public class GioHangController {
    @Autowired
    private CTGioHangService CTGioHangService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private TaiKhoanService taiKhoanService;
    @GetMapping
    public String index(Model model){
        model.addAttribute("sanpham", CTGioHangService.getAll());
        return "GioHang/index";
    }

    // Lấy idUser
    //get giỏ hàng by idUser
    // get ListSP in CartDetail
    //Add sp into cart: (idSP, idCart)
    //Số lượng còn -1; số lượng sp trong GH +1
    //

    @GetMapping("/them-san-pham/{id}")
    public  String themGioHang(@PathVariable Long id, Authentication authentication){

        Taikhoan taikhoan = taiKhoanService.getTaiKhoanByUserName("QUANGVI") ;

        Sanpham product = sanPhamService.getProductByID(id);

        Giohang giohang = gioHangService.getGioHangByUserName("QUANGVI");
        CTGioHangService.addToCart(product,giohang);
        return "redirect:/gio-hang";
    }
    @PostMapping("/cap-nhat-gio-hang")
    public String capNhatGioHang( Authentication authentication,  @RequestParam long id,  @RequestParam int soluong){
        Taikhoan taikhoan = taiKhoanService.getTaiKhoanByUserName("QUANGVI") ;

        Sanpham product = sanPhamService.getProductByID(id);

        Giohang giohang = gioHangService.getGioHangByUserName("QUANGVI");
       // CTGioHangService.addToCart(product,giohang);
        CTGioHangService.capNhatGH(product,giohang, soluong);
        return "redirect:/gio-hang";
    }

    @GetMapping("/xoa- san-pham/{id}")
    public String xoaSanPham(Authentication authentication,  @PathVariable Long id){
        Taikhoan taikhoan = taiKhoanService.getTaiKhoanByUserName("QUANGVI") ;

        Sanpham product = sanPhamService.getProductByID(id);

        Giohang giohang = gioHangService.getGioHangByUserName("QUANGVI");
        CTGioHangService.xoaSanPhamTrongGH(product.getId(),giohang.getMaGH());
        return  "redirect:/gio-hang";
    }


}
