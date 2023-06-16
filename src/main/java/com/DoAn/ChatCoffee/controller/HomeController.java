package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Loaisanpham;
import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.entity.Thuonghieu;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import com.DoAn.ChatCoffee.service.LoaiSanPhamService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.TaiKhoanService;
import com.DoAn.ChatCoffee.service.ThuongHieuService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
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
    private ThuongHieuService thuongHieuService;
    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private ITaiKhoanRepository iTaiKhoanRepository;

    public String saveloginbygoogle(Authentication authentication) {
        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            // Xử lý khi authentication không phải là OAuth2AuthenticationToken
            System.out.println("Xử lý khi authentication không phải là OAuth2AuthenticationToken");
            return "redirect:/";
        }
        else
        {
            // thực hiện lưu tài khoản ở đây
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauth2User = oauthToken.getPrincipal();
            Taikhoan tk = new Taikhoan();
            tk.setEmail(oauth2User.getAttribute("email"));
            Taikhoan existingUser = iTaiKhoanRepository.findByEmail(tk.getEmail());
            if (existingUser == null) {

                if (oauth2User.getAttribute("birthday") == null) {
                    String dateString = "2000-01-01";

                    LocalDate date = LocalDate.parse(dateString);

                    tk.setDateofbirth(date);
                } else {
                    tk.setDateofbirth(oauth2User.getAttribute("birthday"));
                }
                String email = tk.getEmail();
                int atIndex = email.indexOf('@');
                if (atIndex != -1) {
                    String username = email.substring(0, atIndex);
                    tk.setUsername(username);
                }
                tk.setFullname(oauth2User.getAttribute("name"));
                tk.setImgage("");
                tk.setPassword(new BCryptPasswordEncoder().encode(oauth2User.getName()));
                tk.setPhonenumber("0000000000");
                System.out.println("Email" + tk.getEmail() + "Ngay sinh" + tk.getDateofbirth() + "UserName" + tk.getUsername() + "FullName" + tk.getFullname() + "Pass" + tk.getPassword() + "Phone" + tk.getPhonenumber() + " ");
                taiKhoanService.savegg(tk);
            } else {
                System.out.println("Đã có tài khoản");
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
    @GetMapping("/")
    public String homePage(Model model, @Nullable Authentication authentication) {
        saveloginbygoogle(authentication);

        // Hiển thị Menu tất cả sản phẩm nổi bật (sanpham ==8)
        List<Sanpham> sanphams = sanPhamService.getAllProduct();
        Collections.shuffle(sanphams);
        int sanpham = 8;
        if (sanphams.size() > sanpham) {
            sanphams = sanphams.subList(0, sanpham);
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
        if (th.size() > thuonghieu) {
            th = th.subList(0, thuonghieu);
        }
        model.addAttribute("danhsachthuonghieu", th);
        return "home/index";

    }

    //
    @GetMapping("/loai/{id}")
    public String Locloai(@PathVariable Long id, Model model) {
        model.addAttribute("listProducts", sanPhamService.getSanPhamByIdloai(id));
        model.addAttribute("danhsachloai", loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        return "sanpham/index";
    }


    // lọc theo thương hiệu
    @GetMapping("/thuonghieu/{id}")
    public String Locthuonghieu(@PathVariable Long id, Model model) {
        model.addAttribute("listProducts", sanPhamService.getSanPhamByIdThuonghieu(id));
        model.addAttribute("danhsachloai", loaiSanPhamService.getAllCategories());
        model.addAttribute("danhsachthuonghieu", thuongHieuService.getAllThuongHieu());
        return "sanpham/index";
    }

    @GetMapping("/chi-tiet/{id}")
    public String chiTietSP(@PathVariable Long id, Model model) {
        model.addAttribute("sanpham", sanPhamService.getProductByID(id));
        return "sanpham/chitietsp";
    }

}
