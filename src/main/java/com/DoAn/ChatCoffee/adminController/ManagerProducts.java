package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Product;
import com.DoAn.ChatCoffee.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/managerProducts")
public class ManagerProducts {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String index(Model model){
        model.addAttribute("listProducts", productService.getAllProduct());
        return "admin/managerProducts/index";
    }

    @GetMapping("/add")
    public  String addBookForm(Model model){
        model.addAttribute("product", new Product());
        return "admin/managerProducts/add";
    }
    @PostMapping("/add")
    public String addBook( @ModelAttribute("product") Product product){
        productService.saveProduct(product);
        return  "redirect:/admin/managerProducts";

    }

}
