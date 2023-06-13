package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.CTHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICTHoaDonRepository extends JpaRepository<CTHoaDon, Long> {


}
