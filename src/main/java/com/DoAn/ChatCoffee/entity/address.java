package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*@Data
@Entity
@Table(name = "address")*/
public class address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaDC;

    @Column(name = "IdUser")
    private Long IdUser;

    @Column(name = "DiaChi")
    @NotNull(message = "Địa chỉ không được để trống!")
    @Size(max = 120)
    private String DiaChi;

    @Column(name = "DiaChi1")
    @Size(max = 120)
    private String DiaChi1;

    @Column(name = "DiaChi2")
    @Size(max = 120)
    private String DiaChi2;

    //@ManyToOne
    //@JoinColumn(name = "IdUser", referencedColumnName = "Id")
    //private User user;
}
