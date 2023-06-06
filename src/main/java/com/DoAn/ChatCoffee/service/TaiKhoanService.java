package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class TaiKhoanService {

    private ITaiKhoanRepository itaiKhoanRepository;

    @Autowired
    public TaiKhoanService(ITaiKhoanRepository itaiKhoanRepository) {
        this.itaiKhoanRepository = itaiKhoanRepository;
    }


}
