package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import com.DoAn.ChatCoffee.repository.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    IHoaDonRepository hoaDonRepository;

    @Autowired
    ICTHoaDonRepository ctHoaDonRepository;
    public Hoadon getHoaDonByUserName(String username){
        return hoaDonRepository.getHoaDonByUserName(username);
    }

    public List<Hoadon> getListHoaDonByUserName(String username, String trangthai){
        if (trangthai==null)
            return hoaDonRepository.getListHoaDonByUserName(username);
        return hoaDonRepository.getListByUserNameVaTrangThai(username,trangthai);
    }

    public void save(Hoadon hoadon){
        hoaDonRepository.save(hoadon);
    }

    public int slHoaDonByUserNameVaTrangThai(String username, String trangthai){
     //   List<Hoadon> hoadons = getListHoaDonByUserName(username);
        int sl = hoaDonRepository.getListByUserNameVaTrangThai(username,trangthai).size();
        return sl;
    }

}
