package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class TaiKhoanService {
    @Autowired
    private ITaiKhoanRepository itaiKhoanRepository;


    public TaiKhoanService(ITaiKhoanRepository itaiKhoanRepository) {
        this.itaiKhoanRepository = itaiKhoanRepository;
    }

    public Taikhoan getTaiKhoanByUserName(String username){
        return  itaiKhoanRepository.findByUsername(username);
    }


}
