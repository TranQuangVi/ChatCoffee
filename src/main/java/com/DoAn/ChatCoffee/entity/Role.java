package com.DoAn.ChatCoffee.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Size(max = 50,message = "Tên phải ít hơn 50 ký tự")
    @NotBlank(message = "Tên là bắt buộc")
    @Column(name = "name",length = 50,nullable = false)
    private  String name;

    @Size(max = 250,message = "Mô tả phải ít hơn 250 ký tự")
    @Column(name = "description",length = 250)
    private  String description;

    @ManyToMany(mappedBy = "roles")
    private Set<Taikhoan> taikhoans = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Taikhoan> getTaikhoans() {
        return taikhoans;
    }

    public void setTaikhoans(Set<Taikhoan> taikhoans) {
        this.taikhoans = taikhoans;
    }
}