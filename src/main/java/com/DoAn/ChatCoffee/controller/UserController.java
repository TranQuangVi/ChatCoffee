package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ITaiKhoanRepository iTaiKhoanRepository;

    @Autowired
    private TaiKhoanService taiKhoanService;
    @Autowired
    private SanPhamService sanPhamService;
    @GetMapping
    public String index(Model model, Authentication authentication){
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(taiKhoanService.getTaiKhoanByUserName(authentication.getName()).getId()));
        model.addAttribute("listProducts", sanPhamService.getAllProduct());
        return "user/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id ,Model model) {
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(id));
        model.addAttribute("listProducts", sanPhamService.getAllProduct());
        return "user/index";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute("taikhoan") Taikhoan taikhoan){
        taiKhoanService.saveTaikhoan(taikhoan);
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
            taikhoan.setPassword(new
                    BCryptPasswordEncoder().encode(taikhoan.getPassword()));
        taiKhoanService.save(taikhoan);
        return "redirect:/user/login";
    }
}