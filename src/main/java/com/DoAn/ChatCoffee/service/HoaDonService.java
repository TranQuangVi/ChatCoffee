package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.CTHoaDon;
import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import com.DoAn.ChatCoffee.repository.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;


    @Autowired
    ICTHoaDonRepository ctHoaDonRepository;

    public Hoadon getHoaDonByUserName(String username) {
        return hoaDonRepository.getHoaDonByUserName(username);
    }

    public List<Hoadon> getListHoaDonByUserName(String username, String trangthai) {
        if (trangthai == null)
            return hoaDonRepository.getListHoaDonByUserName(username);
        return hoaDonRepository.getListByUserNameVaTrangThai(username, trangthai);
    }

    public List<Hoadon> getAllHoaDon() {
        return hoaDonRepository.findAll();

    }

    public  List<Hoadon> getListByTrangThai(String trangthai){
        if(trangthai==null|| trangthai=="Tất cả"){

            return  hoaDonRepository.findAll();
        }
        return  hoaDonRepository.getListByTrangThai(trangthai);

    }

    public void save(Hoadon hoadon) {
        hoaDonRepository.save(hoadon);
    }

    public int slHoaDonByUserNameVaTrangThai(String username, String trangthai) {
        //   List<Hoadon> hoadons = getListHoaDonByUserName(username);
        int sl = hoaDonRepository.getListByUserNameVaTrangThai(username, trangthai).size();
        return sl;
    }

    // tính tổng tiền trong chi tiết HD
    public Long tongTienTrongGioHang(List<CTGiohang> ctGiohangs) {
        Long tongtien=0L;
        for (var ct : ctGiohangs) {
            tongtien += ct.getSoluong() * ct.getSanpham().getGia();
        }
        return tongtien;
    }
    public Long tongSoluongTrongGioHang(List<CTGiohang> ctGiohangs) {
        Long tongSL=0L;
        for (var ct : ctGiohangs) {
            tongSL += ct.getSoluong();
        }
        return tongSL;
    }

}
