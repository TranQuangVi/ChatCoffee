package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Giohang;
import com.DoAn.ChatCoffee.repository.IGioHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GioHangService {
    @Autowired
    private IGioHangRepository gioHangRepository;
    @Autowired
    private TaiKhoanService taiKhoanService;
    public Giohang getCartByID(Long cart_id){
        Optional<Giohang> optional = gioHangRepository.findById(cart_id);
        return  optional.orElse(null);
    }

    public Giohang getGioHangByUserName(String username){
        return gioHangRepository.getGioHangByUserName(username);
    }




}
