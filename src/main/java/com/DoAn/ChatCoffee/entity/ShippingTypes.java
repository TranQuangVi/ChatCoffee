package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "ShippingTypes")
public class ShippingTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaVT;

    @Column(name = "TenVT")
    @NotEmpty(message = "Tên vận chuyển không được để trống!")
    @Size(max = 50)
    private String TenVT;

    @Column(name = "Gia")
    @NotEmpty(message = "Giá không được để trống!")
    private int Gia;

    @OneToMany(mappedBy = "ShippingTypes", cascade = CascadeType.ALL)
    private List<Bill> bills;
}
