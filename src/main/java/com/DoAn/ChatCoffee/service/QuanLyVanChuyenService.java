package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Vanchuyen;
import com.DoAn.ChatCoffee.repository.IGioHangRepository;
import com.DoAn.ChatCoffee.repository.IQuanLyVanChuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuanLyVanChuyenService {
    @Autowired
    private IQuanLyVanChuyenRepository quanLyVanChuyenRepository;
    //Thêm - sửa
    public void save(Vanchuyen vanchuyen){
        quanLyVanChuyenRepository.save(vanchuyen);
    }

    //xóa
    public void delete(Vanchuyen vanchuyen){
        quanLyVanChuyenRepository.deleteById(vanchuyen.getMaVC());
    }

    //Lấy dah nh sách
    public List<Vanchuyen> getAllVanChuyen(){
        return quanLyVanChuyenRepository.findAll();
    }

}
