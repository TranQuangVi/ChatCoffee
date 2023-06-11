package com.DoAn.ChatCoffee.security;


import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

public class TaiKhoanDetails implements UserDetails {
    private final Taikhoan taikhoan;
    private final ITaiKhoanRepository itaiKhoanRepository;

    public TaiKhoanDetails(Taikhoan taikhoan, ITaiKhoanRepository itaiKhoanRepository) {
        this.taikhoan = taikhoan;
        this.itaiKhoanRepository = itaiKhoanRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String[] roles = itaiKhoanRepository.getRoleOfUser(taikhoan.getId());
        for (String role : roles) {
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
