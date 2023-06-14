package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.service.RoleService;
import com.DoAn.ChatCoffee.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/QuanLyTaiKhoan")
public class QuanLyTaiKhoan {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/page/{pageNo}")
    public String page(Model model, @PathVariable(value = "pageNo") int pageNo){
        int pageSize= 6;
        Page<Taikhoan> page=taiKhoanService.findPaginated(pageNo, pageSize);
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
        List<Taikhoan> Listtaikhoan= page.getContent();
        model.addAttribute("Listtaikhoan", Listtaikhoan);
        return "admin/QuanLyTaiKhoan/index";
    }

    @GetMapping
    public String index(Model model){
        //model.addAttribute("Listtaikhoan",taiKhoanService.getAllTaikhoan());
        return page(model, 1);
    }


    @GetMapping("/add")
    public  String addForm(Model model){
        model.addAttribute("taikhoan", new Taikhoan());
        model.addAttribute("dsroles", roleService.getAllRoles());
        return "admin/QuanLyTaiKhoan/add";
    }
    @PostMapping("/add")
    public String addSubmit( @ModelAttribute("taikhoan") Taikhoan taikhoan) {
        taikhoan.setPassword(new
                BCryptPasswordEncoder().encode(taikhoan.getPassword()));
        taiKhoanService.save(taikhoan);
        taiKhoanService.saveAdmin(taikhoan);

        return  "redirect:/admin/QuanLyTaiKhoan";


    }
    @GetMapping("/editAccount")
    public String editAccount(){
        return "admin/QuanLyTaiKhoan/editAccount";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(){
        return "admin/QuanLyTaiKhoan/deleteAccount";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id , Model model) {
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(id));
        return "/admin/QuanLyTaiKhoan/profile";
    }

    @PostMapping("/profile")
    public String editSubmit(@ModelAttribute("taikhoan") Taikhoan taikhoan){
        taiKhoanService.saveTaikhoan(taikhoan);
        return "redirect:/admin/QuanLyTaiKhoan/profile/1";
    }

    @GetMapping("/edit/{id}")
    public String editRoleForm(@PathVariable Long id, Model model){
        model.addAttribute("taikhoan",taiKhoanService.getTaikhoanByID(id));
        return "admin/QuanLyTaiKhoan/edit";
    }

    @PostMapping("/edit")
    public String editRole(@ModelAttribute("edit") Taikhoan taikhoan) {
        taiKhoanService.saveTaikhoan(taikhoan);
        return "redirect:/admin/QuanLyTaiKhoan";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        taiKhoanService.deleteTaikhoanByID(id);
        return "redirect:/admin/QuanLyTaiKhoan";
    }

    @GetMapping("/loginadmin")
    public String formLogin() {
        return "admin/managerAccounts/loginadmin";
    }
}
