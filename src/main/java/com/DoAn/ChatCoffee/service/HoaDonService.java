package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.repository.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    @Autowired
      private IHoaDonRepository hoaDonRepository;


    public Hoadon getHoaDonByUserName(String username){
        return hoaDonRepository.getHoaDonByUserName(username);
    }

    public List<Hoadon> getListHoaDonByUserName(String username){
        return hoaDonRepository.getListHoaDonByUserName(username);
    }
    public List<Hoadon> getAllHoaDon(){
        return  hoaDonRepository.findAll();

    }

    public  List<Hoadon> gethoadonchoduyet(){
        return  hoaDonRepository.gethoadonchoduyet();
    }
    public  List<Hoadon> gethoadondanggiao(){
        return  hoaDonRepository.gethoadondanggiao();
    }
  public  List<Hoadon> gethoadondahoanthanh(){
    return  hoaDonRepository.gethoadondahoanthanh();
}



    public void save(Hoadon hoadon){
        hoaDonRepository.save(hoadon);
    }

}
