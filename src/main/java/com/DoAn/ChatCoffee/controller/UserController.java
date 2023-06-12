package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.TaiKhoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    /*@GetMapping
    public String index(@PathVariable Long id ,Model model){
        model.addAttribute("dstaikhoan", taiKhoanService.getTaikhoanByID(id));
        return "user/login";
    }*/

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id ,Model model) {
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(id));
        model.addAttribute("listProducts", sanPhamService.getAllProduct());
        return "user/index";
    }
   /* @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id , Model model){
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(id));

        return "user/edit";
    }*/
    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute("taikhoan") Taikhoan taikhoan){
        taiKhoanService.saveTaikhoan(taikhoan);
        return "redirect:/user/edit/1";
    }
    @GetMapping("/login")
    public String formLogin() {
        return "user/login";
    }

    @PostMapping("/login")
    public String formLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        // Kiểm tra xem người dùng có tồn tại và mật khẩu có khớp hay không
        Taikhoan user = iTaiKhoanRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/";
        } else {
            return "redirect:/user/login";
        }
    }

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
        LocalDate minAgeDate = currentDate.minusYears(18);

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
            iTaiKhoanRepository.save(taikhoan);
        return "redirect:/user/login";

    }
}

