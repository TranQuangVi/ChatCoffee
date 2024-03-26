package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cthoadon")
public class CTHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_hoadon")
     Hoadon hoadon;

    @ManyToOne
    @JoinColumn(name = "id_sanpham")
    Sanpham sanpham;

    @Column(name = "soluong")
    private int soluong;
}
