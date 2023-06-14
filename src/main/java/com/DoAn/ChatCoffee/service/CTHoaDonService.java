package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.CTHoaDon;
import com.DoAn.ChatCoffee.entity.Giohang;
import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CTHoaDonService {
    @Autowired
    ICTHoaDonRepository ctHoaDonRepository;

    public void save (List<CTGiohang> ctGiohangs, Hoadon hoadon){
        for (CTGiohang item: ctGiohangs) {
            CTHoaDon ctHoaDon = new CTHoaDon();
            ctHoaDon.setSanpham(item.getSanpham());
            ctHoaDon.setSoluong(item.getSoluong());
            ctHoaDon.setHoadon(hoadon);
            ctHoaDonRepository.save(ctHoaDon);
        }
    }


}
