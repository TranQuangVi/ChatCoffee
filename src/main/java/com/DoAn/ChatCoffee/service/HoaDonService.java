package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    IHoaDonRepository hoaDonRepository;

    public Hoadon getHoaDonByUserName(String username){
        return hoaDonRepository.getHoaDonByUserName(username);
    }

    public List<Hoadon> getListHoaDonByUserName(String username){
        return hoaDonRepository.getListHoaDonByUserName(username);
    }

    public void save(Hoadon hoadon){
        hoaDonRepository.save(hoadon);
    }

}
