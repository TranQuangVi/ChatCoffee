package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CTGiohang")
public class CTGiohang {

    @EmbeddedId
    CartDetailsKey id;

    @ManyToOne
   // @MapsId("giohang")
    @JoinColumn(name = "giohang")
    Giohang giohang;

    @ManyToOne
    //@MapsId("sanpham")
    @JoinColumn(name = "sanpham")
    Sanpham sanpham;

  /*  @Column(name = "TongTien")
    private int TongTien;*/

    @Column(name = "TongSL")
    private int soluong;

    /*@Column(name = "TongSP")
    private int TongSP;*/

}
