package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

//@Data
//@Entity
//@Table(name = "pictures")
public class Pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LinkAnh")
    @Size(max = 100)
    private String LinkAnh;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
}
