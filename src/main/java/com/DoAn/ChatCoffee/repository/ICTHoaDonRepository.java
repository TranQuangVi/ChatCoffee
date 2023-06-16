package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.CTHoaDon;
import com.DoAn.ChatCoffee.entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICTHoaDonRepository extends JpaRepository<CTHoaDon, Long> {
    @Query("SELECT ct FROM CTHoaDon ct WHERE ct.hoadon.taikhoan.username = :username AND ct.hoadon.trangthai = :trangthai ")
    List<CTHoaDon> getListSPByUserNameVaTrangThai(@Param("username")String username, @Param("trangthai")String trangthai);

}
