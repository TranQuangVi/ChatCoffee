package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHoaDonRepository extends JpaRepository<Hoadon, Long> {
    @Query("SELECT r FROM Hoadon r WHERE r.taikhoan.username = ?1")
    Hoadon getHoaDonByUserName(String userName);
    @Query("SELECT b FROM Hoadon b WHERE b.taikhoan.username = ?1")
    List<Hoadon> getListHoaDonByUserName(String userName);

    @Query("SELECT b FROM Hoadon b WHERE b.trangthai = 'Chờ duyệt'")
    List<Hoadon> gethoadonchoduyet();
    @Query("SELECT c FROM Hoadon c WHERE c.trangthai = 'Đang giao'")
    List<Hoadon> gethoadondanggiao();
    @Query("SELECT d FROM Hoadon d WHERE d.trangthai = 'Đã hoàn thành'")
    List<Hoadon> gethoadondahoanthanh();





}

