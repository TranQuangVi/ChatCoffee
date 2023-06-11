package com.DoAn.ChatCoffee.security;

import com.DoAn.ChatCoffee.entity.Role;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class TaiKhoanDetailsImpl implements UserDetails {
    private Taikhoan taikhoan;

    public TaiKhoanDetailsImpl(Taikhoan tk) {
        this.taikhoan = tk;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = taikhoan.getRoles();
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return taikhoan.getPassword();
    }
    @Override
    public String getUsername() {
        return taikhoan.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

