package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import com.DoAn.ChatCoffee.service.GioHangService;
import com.DoAn.ChatCoffee.service.HoaDonService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.TaiKhoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ITaiKhoanRepository iTaiKhoanRepository;
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private SanPhamService sanPhamService;
   /* @Autowired
    GioHangService gioHangService;*/
    @Autowired
    ICTHoaDonRepository ctHoaDonRepository;
    @GetMapping
    public String index(Model model, Authentication authentication){
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(taiKhoanService.getTaiKhoanByUserName(authentication.getName()).getId()));
        // Hiển thị Menu tất cả sản phẩm nổi bật (Top 8 sp, sort theo SL bán)

        List<Sanpham> sanphams = sanPhamService.SoLuongBanGiamDan();
        int sanpham = 16;
        if(sanphams.size() > sanpham){
            sanphams = sanphams.subList(0,sanpham);
        }
        model.addAttribute("listProducts", sanphams);

//        model.addAttribute("listProducts", sanPhamService.getAllProduct());
        model.addAttribute("slSPChoXacNhan", hoaDonService.slHoaDonByUserNameVaTrangThai(authentication.getName(), "Chờ duyệt"));
        model.addAttribute("slSPChoLayHang", hoaDonService.slHoaDonByUserNameVaTrangThai(authentication.getName(), "Chờ lấy hàng"));
        model.addAttribute("slSPDangGiao", hoaDonService.slHoaDonByUserNameVaTrangThai(authentication.getName(), "Đang giao"));
        model.addAttribute("slSPDaDat", hoaDonService.getListHoaDonByUserName(authentication.getName(),null).size());
        model.addAttribute("dsCTHDDaMua", ctHoaDonRepository.getListSPByUserNameVaTrangThai(authentication.getName(), "Hoàn thành"));
        model.addAttribute("dsCTDSChoDuyet", ctHoaDonRepository.getListSPByUserNameVaTrangThai(authentication.getName(), "Chờ duyệt"));
        model.addAttribute("dsCTDSChoLay", ctHoaDonRepository.getListSPByUserNameVaTrangThai(authentication.getName(), "Chờ lấy hàng"));
        model.addAttribute("dsCTDSDangGiao", ctHoaDonRepository.getListSPByUserNameVaTrangThai(authentication.getName(), "Đang giao"));
        model.addAttribute("dsHDDaMua", hoaDonService.getListHoaDonByUserName(authentication.getName(), "Hoàn thành"));
        model.addAttribute("dsChoDuyet", hoaDonService.getListHoaDonByUserName(authentication.getName(), "Chờ duyệt"));
        model.addAttribute("dsChoLay", hoaDonService.getListHoaDonByUserName(authentication.getName(), "Chờ lấy hàng"));
        model.addAttribute("dsDangGiao", hoaDonService.getListHoaDonByUserName(authentication.getName(), "Đang giao"));
        return "user/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id ,Model model) {
        //todo: muốn cái gì dựa trên cái gì,-->đầu vào là gì---> kết quả là gì
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(id));
        model.addAttribute("listProducts", sanPhamService.getAllProduct());
        return "user/index";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute("taikhoan") Taikhoan taikhoan, Authentication authentication){
        Taikhoan luuTK = taiKhoanService.getTaiKhoanByUserName(authentication.getName());
        luuTK.setFullname(taikhoan.getFullname());
        luuTK.setDateofbirth(taikhoan.getDateofbirth());
        luuTK.setPhonenumber(taikhoan.getPhonenumber());
        luuTK.setEmail(taikhoan.getEmail());

        taiKhoanService.saveTaikhoan(luuTK);
        return "redirect:/user";
    }
    @GetMapping("/login")
    public String formLogin() {
        return "user/login";
    }

/*    @PostMapping("/login")
    public String formLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        // Kiểm tra xem người dùng có tồn tại và mật khẩu có khớp hay không
        Taikhoan user = iTaiKhoanRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/";
        } else {
            return "redirect:/user/login";
        }
    }*/
    
    @GetMapping("/register")
    public String formRegister(Model model) {

        model.addAttribute("taikhoan", new Taikhoan());

        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("taikhoan") @Valid Taikhoan taikhoan, BindingResult bindingResult) {

        // Kiểm tra xem tên người dùng có chứa dấu cách và dấu
        String regex = "^[^\\s]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(taikhoan.getUsername());

        // kiểm tra tuổi > 18
        LocalDate currentDate = LocalDate.now();
        LocalDate minAgeDate = currentDate.minusYears(1);

        // Kiểm tra tên người dùng đã tồn tại
        Taikhoan existingUser = iTaiKhoanRepository.findByUsername(taikhoan.getUsername());

        if (bindingResult.hasErrors()) {
            return "user/register";
        }
        else if (!matcher.matches()) {
            // Xử lý khi tên người dùng không hợp lệ
            bindingResult.rejectValue("username", "error.user", "Tên người dùng không chứa khoảng trắng");
            return "user/register";
        }
        if (taikhoan.getDateofbirth().isAfter(minAgeDate)) {
            bindingResult.rejectValue("dateofbirth", "error.user", "Bạn chưa đủ 18 tuổi");
            return "user/register";
        }
        if (existingUser != null) {
            bindingResult.rejectValue("username", "error.user", "Tên người dùng đã tồn tại");
            return "user/register";
        }
        else
            taikhoan.setPassword(new BCryptPasswordEncoder().encode(taikhoan.getPassword()));
        taiKhoanService.save(taikhoan);
        gioHangService.themGioHang(taikhoan);
        return "redirect:/user/login";
    }
}