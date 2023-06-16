package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.CTHoaDon;
import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CTHoaDonService {
    @Autowired
    protected ICTHoaDonRepository ctHoaDonRepository;


    public void save (List<CTGiohang> ctGiohangs, Hoadon hoadon){
        for (CTGiohang item: ctGiohangs) {
            CTHoaDon ctHoaDon = new CTHoaDon();
            ctHoaDon.setSanpham(item.getSanpham());
            ctHoaDon.setSoluong(item.getSoluong());
            ctHoaDon.setHoadon(hoadon);
            ctHoaDonRepository.save(ctHoaDon);
        }
    }
    public List<CTHoaDon> getAllCTHoaDon(){
        return  ctHoaDonRepository.findAll();

    }



    public List<CTHoaDon> getListSPByUserNameVaTrangThai(String username, String trangthai){
        return ctHoaDonRepository.getListSPByUserNameVaTrangThai(username,trangthai);
    }


}
