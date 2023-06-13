package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "taikhoan")
public class Taikhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    @NotBlank(message = "Tên người dùng là bắt buộc")
    @Size(max = 50, message = "Tên người dùng phải ít hơn 50 kí tự")
    private String username;

    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "Mật khẩu là bắt buộc")
    private String password;

    @Column(name = "email", length = 50)
    @NotBlank(message = "Email là bắt buộc")
    @Size(max = 50, message = "Email phải ít hơn 50 kí tự")
    private String email;

    @Column(name = "fullname", length = 50, nullable = false)
    @Size(max = 50, message = "Tên đầy đủ của bạn phải ít hơn 50 ký tự")
    @NotBlank(message = "Tên đầy đủ của bạn là bắt buộc")
    private String fullname;
    @Column(name = "image", length = 50, nullable = true)
    private String imgage;


    @Column(name = "dateofbirth", nullable = false)
    @Past(message = "Ngày sinh của bạn nên ở trong quá khứ")
    @NotNull(message = "Ngày sinh của bạn là bắt buộc")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateofbirth;


    @Column(name = "phonenumber", length = 10, nullable = false)
    @NotBlank(message = "Số điện thoại của bạn là bắt buộc")
    private String phonenumber;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Trạng thái của bạn là bắt buộc")
    private Boolean status = true;

    @OneToMany(mappedBy = "taikhoan", cascade = CascadeType.ALL)
    private List<Diachi> diachis;

    @OneToMany(mappedBy = "taikhoan", cascade = CascadeType.ALL)
    private List<Hoadon> hoadons;

    @OneToOne(mappedBy = "taikhoan")
    private Giohang giohang;
    @ManyToMany
    @JoinTable(name ="user_role",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImgage() {
        return imgage;
    }

    public void setImgage(String imgage) {
        this.imgage = imgage;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Boolean getStatus() {
        return status = false;
    }

    public void setStatus(Boolean status) {
        this.status = false;
    }

    public List<Diachi> getDiachis() {
        return diachis;
    }

    public void setDiachis(List<Diachi> diachis) {
        this.diachis = diachis;
    }

    public List<Hoadon> getHoadons() {
        return hoadons;
    }

    public void setHoadons(List<Hoadon> hoadons) {
        this.hoadons = hoadons;
    }

    public Giohang getGiohangs() {
        return giohang;
    }

    public void setGiohangs(Giohang giohangs) {
        this.giohang = giohangs;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(LocalDate dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    /*public boolean isEnabled()
    {
        return true;
    }*/
}
