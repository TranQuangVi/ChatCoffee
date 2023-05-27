package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import lombok.Data;

//@Data
//@Entity
//@Table(name = "CartDetails")
public class CartDetails {

    @EmbeddedId
    CartDetailsKey id;

//    @ManyToOne
//    @MapsId("CartMaHD")
//    @JoinColumn(name = "MaHD")
//    Cart cart;
//
//    @ManyToOne
//    @MapsId("ProductId")
//    @JoinColumn(name = "id")
//    Product product;

    @Column(name = "TongTien")
    private int TongTien;

    @Column(name = "TongSL")
    private int TongSL;

    @Column(name = "TongSP")
    private int TongSP;

}
