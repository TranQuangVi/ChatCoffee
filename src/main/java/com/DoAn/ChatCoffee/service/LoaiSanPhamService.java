package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Loaisanpham;
import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.repository.ILoaiSanPhamRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiSanPhamService {
    @Autowired
    private ILoaiSanPhamRespository loaiSanPhamRespository;
    public List<Loaisanpham> getAllCategories(){
        return  loaiSanPhamRespository.findAll();

    }
    //Page ListProduct
    public Page<Loaisanpham> findPaginated(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.loaiSanPhamRespository.findAll(pageable);
    }

    public  Loaisanpham getloaiSanphamByid(Long id){
        Optional<Loaisanpham> optionalLoaiSP = loaiSanPhamRespository.findById(id);
        if(optionalLoaiSP.isPresent()){
            return  optionalLoaiSP.get();
        }
        else {
            throw  new RuntimeException("Không tìm thấy loại sản phẩm");
        }


    }
    public  void  addLoaiSP(Loaisanpham id){
        loaiSanPhamRespository.save(id);
    }


    public  Loaisanpham saveLoaiSP(Loaisanpham id){
        return   loaiSanPhamRespository.save(id);

    }
    public  void  deleteLoaiSP(Long id){
        loaiSanPhamRespository.deleteById(id);
    }
}
