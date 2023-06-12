package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Sanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISanPhamRepository extends JpaRepository<Sanpham,Long> {
    @Query("SELECT p FROM Sanpham p WHERE p.TenSP LIKE %?1%")
    List<Sanpham> search(String keyword);
}
