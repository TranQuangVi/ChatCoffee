package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.CartDetailsKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICTGioHangRepository extends JpaRepository<CTGiohang, CartDetailsKey> {

    //todo: xóa sản phẩm trong bản chi tiết giỏ hàng, action(idSP, idGH)
    //todo: xóa hết sản phẩm trong bản chi tiết giỏ hàng, action(idGH)
    @Modifying
    @Transactional
    @Query("DELETE FROM CTGiohang ct WHERE ct.sanpham.id = :idsp AND ct.giohang.MaGH = :idGH")
    void deleteProductInCart(@Param("idsp")Long idsp, @Param("idGH") Long idGH);
}
