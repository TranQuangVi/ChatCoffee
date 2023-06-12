package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.CustomUserDetail;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ITaiKhoanRepository iTaiKhoanRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Taikhoan tk = iTaiKhoanRepository.findByUsername(username);
        if (tk == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(tk, iTaiKhoanRepository) ;
    }
}
