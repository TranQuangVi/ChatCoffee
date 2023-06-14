package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Thuonghieu;
import com.DoAn.ChatCoffee.repository.IThuongHieuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuService {
    @Autowired
    private IThuongHieuRepository thuongHieuRepository;

    public List<Thuonghieu> getAllThuongHieu(){
        return thuongHieuRepository.findAll();
    }

    //Page ListProduct
    public Page<Thuonghieu> findPaginated(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.thuongHieuRepository.findAll(pageable);
    }

    public Thuonghieu getThuongHieuById(Long id){
        Optional<Thuonghieu> optional = thuongHieuRepository.findById(id);
        return optional.orElse(null);
    }

    public void deleteThuongHieu(Long id){
        thuongHieuRepository.deleteById(id);
    }

    public void saveThuongHieu(Thuonghieu thuonghieu){
        thuongHieuRepository.save(thuonghieu);
    }
}
