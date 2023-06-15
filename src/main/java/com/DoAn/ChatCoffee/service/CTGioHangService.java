package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.CartDetailsKey;
import com.DoAn.ChatCoffee.entity.Giohang;
import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.repository.ICTGioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CTGioHangService {
    @Autowired
    ICTGioHangRepository ctGioHangRepository;

    public List<CTGiohang> getAll() {
        return ctGioHangRepository.findAll();
    }
    public List<CTGiohang> getGioHangByUserName(String username){
        return ctGioHangRepository.getGioHangByUserName(username);
    }
    public CTGiohang getItemByID(CartDetailsKey cartDetailsKey) {
        Optional<CTGiohang> optional = ctGioHangRepository.findById(cartDetailsKey);
        return optional.orElse(null);
    }

    public void addToCart(Sanpham product, Giohang cart) {

        //Get key của chi tiết giỏ hàng (idSP, idGH)
        CartDetailsKey cartDetailsKey = new CartDetailsKey();
        cartDetailsKey.setProduct_id(product.getId());
        cartDetailsKey.setCart_id(cart.getMaGH());

        Optional<CTGiohang> optional = ctGioHangRepository.findById(cartDetailsKey);
        CTGiohang cartDetails = new CTGiohang();


        if (optional.isPresent()) {
            cartDetails = optional.get();
            //    cartDetails.setSanpham(product);
            //    cartDetails.setGiohang(cart);
            cartDetails.setSoluong(cartDetails.getSoluong() + 1);
        } else {
            cartDetails.setSanpham(product);
            cartDetails.setGiohang(cart);
            cartDetails.setSoluong(1);
        }
        cartDetails.setId(cartDetailsKey);

        ctGioHangRepository.save(cartDetails);
    }

    public void capNhatGH(Sanpham product, Giohang cart, int soluong) {

        //Get key của chi tiết giỏ hàng (idSP, idGH)
        CartDetailsKey cartDetailsKey = new CartDetailsKey();
        cartDetailsKey.setProduct_id(product.getId());
        cartDetailsKey.setCart_id(cart.getMaGH());

        Optional<CTGiohang> optional = ctGioHangRepository.findById(cartDetailsKey);
        CTGiohang cartDetails = new CTGiohang();
        cartDetails = optional.get();
        cartDetails.setSoluong( soluong);

        cartDetails.setId(cartDetailsKey);

        ctGioHangRepository.save(cartDetails);
    }

    public void xoaSanPhamTrongGH(Long idSP, Long idGH){
        this.ctGioHangRepository.deleteProductInCart(idSP,idGH);
    }
}
