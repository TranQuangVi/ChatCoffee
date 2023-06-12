package com.DoAn.ChatCoffee.entity;

import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetail implements UserDetails {
    private final Taikhoan taikhoan;
    private final ITaiKhoanRepository taiKhoanRepository;
    public CustomUserDetail(Taikhoan taikhoan, ITaiKhoanRepository userRepository){

        this.taikhoan = taikhoan;
        this.taiKhoanRepository = userRepository;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String[] roles = taiKhoanRepository.getRoleOfUser(taikhoan.getId());
        for(String role : roles)
        {
            authorities.add(new SimpleGrantedAuthority(role));
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
        return true;
    }
}
