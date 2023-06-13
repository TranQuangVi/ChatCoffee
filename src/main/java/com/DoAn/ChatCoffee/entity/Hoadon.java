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
@Getter
@Setter
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
}
