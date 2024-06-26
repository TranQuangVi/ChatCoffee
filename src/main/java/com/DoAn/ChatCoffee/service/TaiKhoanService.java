package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.entity.Taikhoan;
import com.DoAn.ChatCoffee.repository.IRoleRepository;
import com.DoAn.ChatCoffee.repository.ITaiKhoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@Service
public class TaiKhoanService {
    @Autowired
    private ITaiKhoanRepository taiKhoanRepository;


    @Autowired
    public TaiKhoanService(ITaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    public Taikhoan getTaiKhoanByUserName(String username) {
        return taiKhoanRepository.findByUsername(username);
    }


    @Autowired
    private IRoleRepository itaiRoleRepository;

    public List<Taikhoan> getAllTaikhoan() {
        return taiKhoanRepository.findAll();

    }

    public Taikhoan getTaikhoanByID(Long user_id) {
        Optional<Taikhoan> optional = taiKhoanRepository.findById(user_id);
        return optional.orElse(null);

    }


    public void saveTaikhoan(Taikhoan taikhoan) {
        taiKhoanRepository.save(taikhoan);
    }

    public void deleteTaikhoanByID(Long user_id) {
        taiKhoanRepository.deleteById(user_id);
    }
//    @Autowired
  /*  public TaiKhoanService(ITaiKhoanRepository itaiKhoanRepository) {
        this.itaiKhoanRepository = itaiKhoanRepository;
    }*/


    public void save(Taikhoan tk) {
        taiKhoanRepository.save(tk);
        Long taiKhoanId = taiKhoanRepository.getUserIdByUsername(tk.getUsername());
        Long roleId = itaiRoleRepository.getRoleIdByName("USER");
        if (taiKhoanId != 0 && roleId != 0) {
            taiKhoanRepository.addRoleToUser(taiKhoanId, roleId);
        }
    }

    public void saveAdmin(Taikhoan tk) {
        taiKhoanRepository.save(tk);
        Long taikhoanId = tk.getId();
        Long roleId = itaiRoleRepository.getRoleIdByName("ADMIN");
        if (taikhoanId != 0 && roleId != 0) {
            taiKhoanRepository.addRoleToUser(taikhoanId, roleId);
        }
    }

    //Page ListProduct
    public Page<Taikhoan> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.taiKhoanRepository.findAll(pageable);
    }

}
