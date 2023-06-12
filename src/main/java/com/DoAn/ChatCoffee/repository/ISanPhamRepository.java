package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Sanpham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISanPhamRepository extends JpaRepository<Sanpham,Long> {

}
