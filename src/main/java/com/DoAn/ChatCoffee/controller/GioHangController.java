package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.*;
import com.DoAn.ChatCoffee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    private QuanLyThanhToanService thanhToanService;
    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private CTHoaDonService ctHoaDonService;

    @Autowired
    private QuanLyVanChuyenService vanChuyenService;
    @GetMapping
    public String index(Model model, Authentication authentication){
        List<CTGiohang> ctGiohangs= CTGioHangService.getGioHangByUserName(authentication.getName());
        model.addAttribute("ctGiohangs", ctGiohangs);
        model.addAttribute("TongTien", hoaDonService.tongTienTrongGioHang(ctGiohangs));
        return "GioHang/index";
    }

    @GetMapping("/them-san-pham/{id}")
    public  String themGioHang(@PathVariable Long id, Authentication authentication){
        Sanpham product = sanPhamService.getProductByID(id);
        Giohang giohang = gioHangService.getGioHangByUserName(authentication.getName());
        CTGioHangService.addToCart(product,giohang);
        return "redirect:/gio-hang";
    }
    @PostMapping("/cap-nhat-gio-hang")
    public String capNhatGioHang( Authentication authentication,  @RequestParam long id,  @RequestParam int soluong){
        Sanpham product = sanPhamService.getProductByID(id);
        Giohang giohang = gioHangService.getGioHangByUserName(authentication.getName());
        CTGioHangService.capNhatGH(product,giohang, soluong);
        return "redirect:/gio-hang";
    }

    @GetMapping("/xoa-san-pham/{id}")
    public String xoaSanPham(Authentication authentication,  @PathVariable Long id){
        Sanpham product = sanPhamService.getProductByID(id);
        Giohang giohang = gioHangService.getGioHangByUserName(authentication.getName());
        CTGioHangService.xoaSanPhamTrongGH(product.getId(),giohang.getMaGH());
        return  "redirect:/gio-hang";
    }
    @GetMapping("/xoa-tat-ca-san-pham")
    public String xoaTatCaSanPham(Authentication authentication){
        Giohang giohang = gioHangService.getGioHangByUserName(authentication.getName());
        CTGioHangService.xoaTatCaSP(giohang.getMaGH());
        return  "redirect:/gio-hang";
    }
    @GetMapping("/tao-don-hang")
    public String muaForm(Authentication authentication, Model model){
        Taikhoan taikhoan = taiKhoanService.getTaiKhoanByUserName(authentication.getName());
        model.addAttribute("ctGiohangs", CTGioHangService.getGioHangByUserName(taikhoan.getUsername()));
        model.addAttribute("hoadon", new Hoadon());
        model.addAttribute("taikhoan", taikhoan);
        model.addAttribute("thanhtoans", thanhToanService.getAllThanhToan());
        model.addAttribute("vanchuyens", vanChuyenService.getAllVanChuyen());
        model.addAttribute("TongTien", hoaDonService.tongTienTrongGioHang(CTGioHangService.getGioHangByUserName(taikhoan.getUsername())));
        return "giohang/hoadon";
    }

    @PostMapping("/luu-don-hang")
    public String muaSubmit(@ModelAttribute("taikhoan") Taikhoan taikhoan,
                            @ModelAttribute("hoadon") Hoadon hoadon, Authentication authentication){
        List<CTGiohang> ctGiohangs= CTGioHangService.getGioHangByUserName(authentication.getName());
        hoadon.setTaikhoan(taiKhoanService.getTaiKhoanByUserName(authentication.getName()));
        hoadon.setNgaydat(LocalDate.now());
        hoadon.setNgaygiao(LocalDate.now());
        hoadon.setTonggia(hoaDonService.tongTienTrongGioHang(ctGiohangs)+hoadon.getVanchuyen().getGia());
        hoadon.setTongsoluong(hoaDonService.tongSoluongTrongGioHang(ctGiohangs));
        hoadon.setTrangthai("Chờ duyệt");
        hoaDonService.save(hoadon);
        ctHoaDonService.save(ctGiohangs,hoadon);
        return "redirect:/user";
    }
}
