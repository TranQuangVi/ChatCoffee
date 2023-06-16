package com.DoAn.ChatCoffee.service;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.repository.ISanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {
    @Autowired
    private ISanPhamRepository sanPhamRepository;

    public List<Sanpham> getAllProduct(){
        return sanPhamRepository.findAll();
    }
    public Sanpham getProductByID(Long product_id){
        Optional<Sanpham> optional = sanPhamRepository.findById(product_id);
        return  optional.orElse(null);
    }


    public void saveProduct(Sanpham product){
        product.setSldaban(0L);
        if(product.getSoluong()==null)
            product.setSoluong(0L);
        sanPhamRepository.save(product);
    }
     void privateSaveProduct(Sanpham product){
        sanPhamRepository.save(product);
    }

    public void deleteProductByID(Long product_id){
        this.sanPhamRepository.deleteById(product_id);
    }

   /* public List<Sanpham> getSearchListProduct(String search){
        if(search != null){
            return sanPhamRepository.search(search);
        }
        return sanPhamRepository.findAll();
    }*/
    //Page ListProduct
   
    //Page ListProduct
    public Page<Sanpham> findPaginated(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.sanPhamRepository.findAll(pageable);
    }
    public Page<Sanpham> findPaginated(int pageNo, int pageSize, String searchstring) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (searchstring == null)
            return this.sanPhamRepository.findAll(pageable);
        Page<Sanpham> searchResult = sanPhamRepository.searchPage(searchstring, pageable);
        return searchResult;
    }

    // lấy sản phẩm theo ID loại

    public  List<Sanpham> getSanPhamByIdloai( Long Id_loai)
    {

        return  sanPhamRepository.getSanPhamByIdloai(Id_loai);
    }
    // lấy sản phẩm theo Id thương hiệu
    public List<Sanpham> getSanPhamByIdThuonghieu(Long Id_TH){
        return  sanPhamRepository.getSanPhamByIdThuonghieu(Id_TH);
    }
    //lấy sản phẩm theo giá

    public  List<Sanpham> getSanphamtheoGia(Long id){
        List<Sanpham> sanpham = null;
        switch (id.intValue()){
            case  1:
                sanpham = sanPhamRepository.getSanphamGia1();
                break;
            case  2:
                sanpham = sanPhamRepository.getSanphamGia2();
                break;
            case  3:
                sanpham = sanPhamRepository.getSanphamGia3();
                break;
            case  4:
                sanpham = sanPhamRepository.getSanphamGia4();
                break;
        }
        return sanpham;}


    // lấy sản phẩm theo ký tụ
    public  List<Sanpham> getSanphamtheotensp(String id) {
        List<Sanpham> sanphams = null;
        switch (id) {
            case "1":
                sanphams = sanPhamRepository.getSanphamtuAZ();
                break;
            case "2":
                sanphams = sanPhamRepository.getSanphamtuZA();
                break;
        }
        return sanphams;
    }

    //Top 8 sản phẩm có lượt bán cáo nhất
    public List<Sanpham> SoLuongBanGiamDan(){
        return sanPhamRepository.SoLuongBanGiamDan();
    }  public List<Sanpham> GiaBanDeXuat(){
        return sanPhamRepository.GiaBanDeXuat();
    }

}
