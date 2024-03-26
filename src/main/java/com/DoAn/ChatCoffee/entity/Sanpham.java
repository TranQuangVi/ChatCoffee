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

    @Transient
    public String getPhotosImagePath() {
        if (anh == null || id == null) return null;

        return "src/main/resources/static/images/products/" + id + "/" + anh;
    }
    @OneToMany(mappedBy = "sanpham", cascade = CascadeType.ALL)
    private List<CTHoaDon> ctHoaDons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

    public Long getGia() {
        return Gia;
    }

    public void setGia(Long gia) {
        Gia = gia;
    }

    public Long getSoluong() {
        return Soluong;
    }

    public void setSoluong(Long soluong) {
        Soluong = soluong;
    }

    public Long getKhoiluong() {
        return Khoiluong;
    }

    public void setKhoiluong(Long khoiluong) {
        Khoiluong = khoiluong;
    }

    public String getXuatxu() {
        return Xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        Xuatxu = xuatxu;
    }

    public Long getHSD() {
        return HSD;
    }

    public void setHSD(Long HSD) {
        this.HSD = HSD;
    }

    public Long getSldaban() {
        return Sldaban;
    }

    public void setSldaban(Long sldaban) {
        Sldaban = sldaban;
    }

    public String getDangSP() {
        return DangSP;
    }

    public void setDangSP(String dangSP) {
        DangSP = dangSP;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        Mota = mota;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }

    public Loaisanpham getLoaisanpham() {
        return loaisanpham;
    }

    public void setLoaisanpham(Loaisanpham loaisanpham) {
        this.loaisanpham = loaisanpham;
    }

    public Thuonghieu getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(Thuonghieu thuonghieu) {
        this.thuonghieu = thuonghieu;
    }
}
