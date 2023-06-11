package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.IRoleRepository;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class TaiKhoanService {

    @Autowired
    private ITaiKhoanRepository itaiKhoanRepository;

    @Autowired
    private IRoleRepository itaiRoleRepository;

    @Autowired
    public TaiKhoanService(ITaiKhoanRepository itaiKhoanRepository) {
        this.itaiKhoanRepository = itaiKhoanRepository;
    }


    public void save (Taikhoan tk) {
        itaiKhoanRepository.save(tk);
        Long taiKhoanId = itaiKhoanRepository.getUserIdByUsername(tk.getUsername());
        Long roleId = itaiRoleRepository.getRoleIdByName("USER");
        if(taiKhoanId != 0 && roleId !=0)
        {
            itaiKhoanRepository.addRoleToUser(taiKhoanId, roleId);
        }
    }

}
