package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.service.RoleService;
import com.DoAn.ChatCoffee.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/managerAccounts")
public class ManagerAccounts {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @Autowired
    private RoleService roleService;
    @GetMapping
    public String index(Model model){
        model.addAttribute("Listtaikhoan",taiKhoanService.getAllTaikhoan());

        return "admin/managerAccounts/index";
    }


    @GetMapping("/add")
    public  String addForm(Model model){
        model.addAttribute("taikhoan", new Taikhoan());
        model.addAttribute("dsroles", roleService.getAllRoles());
        return "admin/managerAccounts/add";
    }
    @PostMapping("/add")
    public String addSubmit( @ModelAttribute("taikhoan") Taikhoan taikhoan) {
        taikhoan.setPassword(new
                BCryptPasswordEncoder().encode(taikhoan.getPassword()));
        taiKhoanService.save(taikhoan);
        taiKhoanService.saveAdmin(taikhoan);

        return  "redirect:/admin/managerAccounts";


    }
    @GetMapping("/editAccount")
    public String editAccount(){
        return "admin/managerAccounts/editAccount";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(){
        return "admin/managerAccounts/deleteAccount";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id , Model model) {
        model.addAttribute("taikhoan", taiKhoanService.getTaikhoanByID(id));
        return "/admin/managerAccounts/profile";
    }

    @PostMapping("/profile")
    public String editSubmit(@ModelAttribute("taikhoan") Taikhoan taikhoan){
        taiKhoanService.saveTaikhoan(taikhoan);
        return "redirect:/admin/managerAccounts/profile/1";
    }

    @GetMapping("/edit/{id}")
    public String editRoleForm(@PathVariable Long id, Model model){
        model.addAttribute("taikhoan",taiKhoanService.getTaikhoanByID(id));
        return "admin/managerAccounts/edit";
    }

    @PostMapping("/edit")
    public String editRole(@ModelAttribute("edit") Taikhoan taikhoan) {
        taiKhoanService.saveTaikhoan(taikhoan);
        return "redirect:/admin/managerAccounts";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        taiKhoanService.deleteTaikhoanByID(id);
        return "redirect:/admin/managerAccounts";
    }

    @GetMapping("/loginadmin")
    public String formLogin() {
        return "admin/managerAccounts/loginadmin";
    }
}
