package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "categoryProduct")
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TenLoai")
    @NotEmpty(message = "Tên loại không được để trống!")
    @Size(max = 30)
    private String TenLoai;

    @Column(name = "Anh")
    @Size(max = 100)
    private String Anh;

    @OneToMany(mappedBy = "categoryProduct", cascade = CascadeType.ALL)
    private List<Product> products;
}
