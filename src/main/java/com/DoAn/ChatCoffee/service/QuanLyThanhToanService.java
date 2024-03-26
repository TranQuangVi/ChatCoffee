package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Thanhtoan;
import com.DoAn.ChatCoffee.repository.IQuanLyThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuanLyThanhToanService {
    @Autowired
    private IQuanLyThanhToanRepository quanLyThanhToanRepository;
    public void save(Thanhtoan thanhtoan){
        quanLyThanhToanRepository.save(thanhtoan);
    }

    //xóa
    public void delete(Long id){
        quanLyThanhToanRepository.deleteById(id);
    }

    //Lấy danh sách
    public List<Thanhtoan> getAllThanhToan(){
        return quanLyThanhToanRepository.findAll();
    }

    //Page ListProduct
    public Page<Thanhtoan> findPaginated(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.quanLyThanhToanRepository.findAll(pageable);
    }

    //Lấy 1 thanh toán
    public Thanhtoan getThanhToanById(Long id){
        Optional<Thanhtoan> optionalVanchuyen = quanLyThanhToanRepository.findById(id);
        if(optionalVanchuyen.isPresent()){
            return optionalVanchuyen.get();
        }
        else {
            throw new RuntimeException("Không tìm thấy phương thức thanh toán này!");
        }
    }
}
