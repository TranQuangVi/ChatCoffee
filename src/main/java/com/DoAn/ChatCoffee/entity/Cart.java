package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaGH;

    @Column(name = "TongTien")
    private int TongTien;

    @Column(name = "TongSL")
    private int TongSL;

    @Column(name = "TongSP")
    private int TongSP;



    @OneToMany(mappedBy = "Cart", cascade = CascadeType.ALL)
    private List<CartDetails> cartDetails;
}
