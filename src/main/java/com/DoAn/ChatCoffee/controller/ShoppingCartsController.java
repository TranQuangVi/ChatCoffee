package com.DoAn.ChatCoffee.controller;

import com.DoAn.ChatCoffee.entity.Giohang;
import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.CTGioHangService;
import com.DoAn.ChatCoffee.service.GioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ShoppingCarts")
public class ShoppingCartsController {
    @Autowired
    private CTGioHangService CTGioHangService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private GioHangService gioHangService;
    @GetMapping
    public String index(Model model){
        model.addAttribute("productInCarts", CTGioHangService.getAll());
        return "ShoppingCart/index";
    }

    // Lấy idUser
    //get giỏ hàng by idUser
    // get ListSP in CartDetail
    //Add sp into cart: (idSP, idCart)
    //Số lượng còn -1; số lượng sp trong GH +1
    //

    @GetMapping("/themGioHang/{id}")
    public  String themGioHang(@PathVariable Long id){
        Sanpham product = sanPhamService.getProductByID(id);
        Giohang cart = gioHangService.getCartByID(1L);
        CTGioHangService.addToCart(product,cart);
        return "redirect:/ShoppingCarts";
    }


}
