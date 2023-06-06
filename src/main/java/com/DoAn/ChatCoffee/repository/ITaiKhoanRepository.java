package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaiKhoanRepository extends JpaRepository<Taikhoan, Long> {

    @Query("SELECT tk FROM Taikhoan tk WHERE tk.username = :username")
    public Taikhoan findByUsername(@Param("username") String username);

}
