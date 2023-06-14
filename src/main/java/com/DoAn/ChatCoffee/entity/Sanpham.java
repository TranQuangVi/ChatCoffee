package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Sanpham")
public class Sanpham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TenSP")
    @NotEmpty(message = "Không được để trống")
    @Size(max = 100)
    private String TenSP;

    @Column(name = "Gia")
    private Long Gia;

    @Column(name = "Soluong")
    private Long Soluong;

    @Column(name = "Khoiluong")
    private Long Khoiluong;

    @Column(name = "Xuatxu")
    @Size(max = 50)
    private String Xuatxu;

    @Column(name = "HSD")
    private Long HSD;

    @Column(name = "Sldaban")
    private Long Sldaban;

    @Column(name = "DangSP")
    @Size(max = 50)
    private String DangSP;

    @Column(name = "Mota")
    @Size(max = 500)
    private String Mota;

    @Column(name = "anh")
    @Size(max = 100)
    private String anh;

    @Column(name = "trangthai")
    private Boolean trangthai;



    @ManyToOne
    @JoinColumn(name = "loaisp_id")
    private Loaisanpham loaisanpham;


    @ManyToOne
    @JoinColumn(name = "thuonghieu_id")
    private Thuonghieu thuonghieu;


    @OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
    private List<CTGiohang> ctGiohangs;

    @OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
    private List<CTHoaDon> ctHoaDons;
}
