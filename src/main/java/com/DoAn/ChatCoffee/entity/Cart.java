package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;

    @Column(name = "TongTien")
    private int TongTien;

    @Column(name = "TongSL")
    private int TongSL;

    @Column(name = "TongSP")
    private int TongSP;


    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartDetails> cartDetails;

}
