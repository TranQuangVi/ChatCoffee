package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Sanpham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ISanPhamRepository extends JpaRepository<Sanpham,Long> {
    // lấy sản phẩm theo Id loại trong bảng sản phẩm
    @Query("Select sp  From  Sanpham sp where sp.loaisanpham.id = ?1")
    List<Sanpham> getSanPhamByIdloai(Long id);

    @Query("Select s  From  Sanpham s where s.thuonghieu.id = ?1")
    List<Sanpham> getSanPhamByIdThuonghieu(Long id);


    // lấy sản phẩm lọc theo giá sản phẩm
    @Query("SELECT p FROM Sanpham p Where p.Gia >= 0 AND p.Gia < 100 ")

    List<Sanpham> getSanphamGia1();

    @Query("SELECT a FROM Sanpham a WHERE a.Gia >= 100 AND a.Gia < 300")
    List<Sanpham> getSanphamGia2();
    @Query("SELECT b FROM Sanpham b WHERE b.Gia >= 300 ")
    List<Sanpham> getSanphamGia3();
    @Query("SELECT b FROM Sanpham b WHERE b.Gia >0")
    List<Sanpham> getSanphamGia4();

    // lấy sản phẩm lọc theo ký tự (a-Z)
    @Query("SELECT t FROM Sanpham t  ORDER BY t.TenSP")
    List<Sanpham> getSanphamtuAZ();
    @Query("SELECT tp FROM Sanpham tp ORDER BY tp.TenSP desc")
    List<Sanpham> getSanphamtuZA();

    @Query("SELECT s FROM Sanpham s WHERE s.TenSP LIKE %:searchstring%")
    Page<Sanpham> searchPage(@Param("searchstring") String searchstring, Pageable pageable);
    @Query("SELECT p FROM Sanpham p WHERE p.TenSP LIKE %?1%")
    List<Sanpham> search(String keyword);

    @Query("SELECT  sp FROM  Sanpham sp  ORDER BY sp.Sldaban desc")
    List<Sanpham> SoLuongBanGiamDan();



}
