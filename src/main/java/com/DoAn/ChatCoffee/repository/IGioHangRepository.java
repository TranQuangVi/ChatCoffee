package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Giohang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IGioHangRepository extends JpaRepository<Giohang, Long> {
    @Query("SELECT r FROM Giohang r WHERE r.taikhoan.username = ?1")
    Giohang getGioHangByUserName(String userName);
}
