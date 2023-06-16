package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import com.DoAn.ChatCoffee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/QuanLyHoaDon")
public class QuanLyHoaDon {
    @Autowired
    private ITaiKhoanRepository iTaiKhoanRepository;
    @Autowired
     private HoaDonService hoaDonService;
    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
     private  ICTHoaDonRepository ctHoaDonRepository;
    @GetMapping
    public String TrangThaiduyet(Model model, Authentication authentication, @Param("trangthai") String trangthai){
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(taiKhoanService.getTaiKhoanByUserName(authentication.getName()).getId()));
        model.addAttribute("listProducts", sanPhamService.getAllProduct());
        model.addAttribute("slSPDaDat", hoaDonService.getListHoaDonByUserName(authentication.getName(),null).size());
        model.addAttribute("dsCTHDDaMua", ctHoaDonRepository.getListSPByUserNameVaTrangThai(authentication.getName(), "Hoàn thành"));
        model.addAttribute("ListHoaDon", hoaDonService.getListByTrangThai(trangthai));
        if(trangthai==null)
            trangthai="Tất cả";
        model.addAttribute("trangthai", trangthai);
        return "admin/QuanLyHoaDon/index";
    }

}
