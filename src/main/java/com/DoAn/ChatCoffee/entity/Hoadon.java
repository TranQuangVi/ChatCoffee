package com.DoAn.ChatCoffee.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "hoadon")
public class Hoadon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long MAHD;

    // ngaydat
    @Column(name = "ngaydat",length = 100)
    @NotEmpty(message = "Không được để trống")
    @Size(max = 9,message = "Ngày đặt không vượt quá 9 ký tự ")
    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDate  NGAYDAT;

    // ngay giao
    @Column(name = "ngaygiao",length = 100)
    @NotEmpty(message = "Không được để trống")
    @Size(max = 9,message = "Ngày giao không vượt quá 9 ký tự ")
    @JsonFormat(pattern="yyyy-MM-dd ")
    private LocalDate  NGAYGIAO;

    // trạng thái
    @Column(name = "trangthai",length = 100)
    @NotNull(message = "Không được để trống")
    @Size(max = 50)
    private String  TRANGTHAI;

    // sdt dat
    @Column(name = "sdt",length = 100)
    @NotNull(message = "Không được để trống")
    @Size(max = 12)
    private String  SDTDAT;

    //diachidat
    @Column(name = "diachi",length = 100)
    @NotNull(message = "Không được để trống")
    @Size(max = 100)
    private String  DIACHI;

    // tong don gia
    @Column(name = "tongdongia",length = 100)
    @NotNull(message = "Không được để trống")
    @Size(max = 12)
    private String  TONGDONGIA;

    // soluong
    @Column(name = "soluong",length = 100)
    @Size(max = 12)
    private String  SOLUONG;

    // giá
    @Column(name = "gia",length = 100)
    @Size(max = 12)
    private String  GIA;

    // -- mã thanh toan/
    @ManyToOne
    @JoinColumn(name = "phuongthuc",referencedColumnName = "id")
    private Thanhtoan Thanhtoan;

    @ManyToOne
    @JoinColumn(name = "vanchuyen",referencedColumnName = "MaVC")
    private Vanchuyen vanchuyen;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private Taikhoan taikhoan;





}
