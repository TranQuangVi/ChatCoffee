package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Giohang")
public class Giohang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaGH;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private Taikhoan taikhoan;

    @OneToMany(mappedBy = "giohang", cascade = CascadeType.ALL)
    private List<CTGiohang> ctGiohangs;

    public Long getMaGH() {
        return MaGH;
    }

    public void setMaGH(Long maGH) {
        MaGH = maGH;
    }

    public Taikhoan getTaikhoan() {
        return taikhoan;
    }

    public void setTaikhoan(Taikhoan taikhoan) {
        this.taikhoan = taikhoan;
    }


}
