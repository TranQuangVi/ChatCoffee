package com.DoAn.ChatCoffee.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "hoadon")
public class Hoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    // ngaydat
    @Column(name = "ngaydat")
    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDate  ngaydat;

    // ngay giao
    @Column(name = "ngaygiao")
    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDate  ngaygiao;

    // trạng thái
    @Column(name = "trangthai")
    private String  trangthai;

    // sdt dat
    @Column(name = "sdt")
    @Size(max = 10)
    private String  sdt;

    //diachidat
    @Column(name = "diachinhan")
    @NotNull(message = "dia chi  không được để trống")
    private String  diachinhan;

    // tong don gia
    @Column(name = "tonggia")
    private Long  tonggia;

    // soluong
    @Column(name = "tongsoluong")
    private Long tongsoluong;

    // -- mã thanh toan/
    @ManyToOne
    @JoinColumn(name = "id_thanhtoan",referencedColumnName = "id")
    private Thanhtoan thanhtoan;

    @ManyToOne
    @JoinColumn(name = "id_vanchuyen",referencedColumnName = "MaVC")
    private Vanchuyen vanchuyen;

    @ManyToOne
    @JoinColumn(name = "id_taikhoan", referencedColumnName = "id")
    private Taikhoan taikhoan;

    @OneToMany(mappedBy = "hoadon", cascade = CascadeType.ALL)
    private List<CTHoaDon> ctHoaDons;

    public Hoadon(Long id, LocalDate ngaydat, LocalDate ngaygiao, String trangthai, String sdt, String diachinhan, Long tonggia, Long tongsoluong, Thanhtoan thanhtoan, Vanchuyen vanchuyen, Taikhoan taikhoan, List<CTHoaDon> ctHoaDons) {
        this.id = id;
        this.ngaydat = ngaydat;
        this.ngaygiao = ngaygiao;
        this.trangthai = trangthai;
        this.sdt = sdt;
        this.diachinhan = diachinhan;
        this.tonggia = tonggia;
        this.tongsoluong = tongsoluong;
        this.thanhtoan = thanhtoan;
        this.vanchuyen = vanchuyen;
        this.taikhoan = taikhoan;
        this.ctHoaDons = ctHoaDons;
    }

    public Hoadon() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(LocalDate ngaydat) {
        this.ngaydat = ngaydat;
    }

    public LocalDate getNgaygiao() {
        return ngaygiao;
    }

    public void setNgaygiao(LocalDate ngaygiao) {
        this.ngaygiao = ngaygiao;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachinhan() {
        return diachinhan;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public Long getTonggia() {
        return tonggia;
    }

    public void setTonggia(Long tonggia) {
        this.tonggia = tonggia;
    }

    public Long getTongsoluong() {
        return tongsoluong;
    }

    public void setTongsoluong(Long tongsoluong) {
        this.tongsoluong = tongsoluong;
    }

    public Thanhtoan getThanhtoan() {
        return thanhtoan;
    }

    public void setThanhtoan(Thanhtoan thanhtoan) {
        this.thanhtoan = thanhtoan;
    }

    public Vanchuyen getVanchuyen() {
        return vanchuyen;
    }

    public void setVanchuyen(Vanchuyen vanchuyen) {
        this.vanchuyen = vanchuyen;
    }

    public Taikhoan getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(Taikhoan taikhoan) {
        this.taikhoan = taikhoan;
    }
}
