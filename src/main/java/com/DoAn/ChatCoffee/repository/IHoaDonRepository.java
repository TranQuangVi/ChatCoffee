package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Hoadon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHoaDonRepository extends JpaRepository<Hoadon, Long> {
    @Query("SELECT r FROM Hoadon r WHERE r.taikhoan.username = ?1")
    Hoadon getHoaDonByUserName(String userName);
    @Query("SELECT b FROM Hoadon b WHERE b.taikhoan.username = ?1")
    List<Hoadon> getListHoaDonByUserName(String userName);

    @Query("SELECT hoadon FROM Hoadon hoadon WHERE hoadon.taikhoan.username = :username AND hoadon.trangthai = :trangthai ")
    List<Hoadon> getListByUserNameVaTrangThai(@Param("username")String username, @Param("trangthai")String trangthai);

    //Quốc
    @Query("SELECT b FROM Hoadon b WHERE b.trangthai = 'Chờ duyệt'")
    List<Hoadon> gethoadonchoduyet();
    @Query("SELECT c FROM Hoadon c WHERE c.trangthai = 'Đang giao'")
    List<Hoadon> gethoadondanggiao();
    @Query("SELECT d FROM Hoadon d WHERE d.trangthai = 'Đã hoàn thành'")
    List<Hoadon> gethoadondahoanthanh();

    @Query("SELECT COUNT(*)  FROM Hoadon b WHERE b.trangthai = 'Chờ duyệt' ")
    Long getCountHoaDonDuyet();
    @Query("SELECT COUNT(*)  FROM Hoadon b WHERE b.trangthai = 'Đang giao' ")
    Long getCountHoaDonGiao();
    @Query("SELECT COUNT(*)  FROM Hoadon b WHERE b.trangthai = 'Đã hoàn thành' ")
    Long getCountHoaDonHoanthanh();


}

