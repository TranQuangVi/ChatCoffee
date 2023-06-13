package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Giohang;
import com.DoAn.ChatCoffee.entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHoaDonRepository extends JpaRepository<Hoadon, Long> {
    @Query("SELECT r FROM Hoadon r WHERE r.taikhoan.username = ?1")
    Hoadon getHoaDonByUserName(String userName);
    @Query("SELECT r FROM Hoadon r WHERE r.taikhoan.username = ?1")
    List<Hoadon> getListHoaDonByUserName(String userName);
}

