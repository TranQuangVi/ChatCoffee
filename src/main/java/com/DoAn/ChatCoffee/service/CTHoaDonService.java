package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.CTGiohang;
import com.DoAn.ChatCoffee.entity.CTHoaDon;
import com.DoAn.ChatCoffee.entity.Hoadon;
import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.repository.ICTHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CTHoaDonService {
    @Autowired
    private ICTHoaDonRepository ctHoaDonRepository;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private CTGioHangService ctGioHangService;

    public void save (List<CTGiohang> ctGiohangs, Hoadon hoadon){
        Long idGH=0L;
        for (CTGiohang item: ctGiohangs) {
            // trừ sl còn, tăng số lượng mua
            Sanpham sanpham = new Sanpham();
            sanpham = sanPhamService.getProductByID(item.getSanpham().getId());
            sanpham.setSoluong(sanpham.getSoluong()-item.getSoluong());
            sanpham.setSldaban(sanpham.getSldaban()+item.getSoluong());
            sanPhamService.saveProduct(sanpham);

            // chi tiếc hóa đơn
            CTHoaDon ctHoaDon = new CTHoaDon();
            ctHoaDon.setSanpham(item.getSanpham());
            ctHoaDon.setSoluong(item.getSoluong());
            ctHoaDon.setHoadon(hoadon);
            ctHoaDonRepository.save(ctHoaDon);
            idGH=item.getGiohang().getMaGH();
        }
        // xóa giỏ hàng
        ctGioHangService.xoaTatCaSP(idGH);
    }
    public List<CTHoaDon> getAllCTHoaDon(){
        return  ctHoaDonRepository.findAll();

    }



    public List<CTHoaDon> getListSPByUserNameVaTrangThai(String username, String trangthai){
        return ctHoaDonRepository.getListSPByUserNameVaTrangThai(username,trangthai);
    }


}
