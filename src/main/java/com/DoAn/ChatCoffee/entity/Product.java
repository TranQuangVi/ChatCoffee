package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "TenSP")
    @NotEmpty(message = "Tên sản phẩm không được để trống!")
    @Size(max = 100)
    private String TenSP;

    @Column(name = "Gia")
    private Long Gia;
/*
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
*/
  /*  @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryProduct categoryProduct;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Pictures> pictures;
    @ManyToOne
    @JoinColumn(name = "brands_id")
    private brands brands;*/


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartDetails> cartDetails;

}
