package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Vanchuyen;
import com.DoAn.ChatCoffee.service.LoaiSanPhamService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import com.DoAn.ChatCoffee.service.ThuongHieuService;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin/quan-ly-san-pham")
public class QuanLySanPham {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;
    @Autowired
    private ThuongHieuService thuongHieuService;
    @GetMapping
    public String index(Model model){
        model.addAttribute("dssanpham", sanPhamService.getAllProduct());
        return "admin/quanlysanpham/index";
    }

    @GetMapping("/add")
    public  String addForm(Model model){
        model.addAttribute("sanpham", new Sanpham());
        model.addAttribute("dsloaisp", loaiSanPhamService.getAllCategories());
        model.addAttribute("dsthuonghieu", thuongHieuService.getAllThuongHieu());
        return "admin/quanlysanpham/add";
    }
    @PostMapping("/add")
    public String addSubmit( @ModelAttribute("sanpham") Sanpham sanpham, @RequestParam("image") MultipartFile file) throws IOException{
        sanPhamService.saveProduct(sanpham);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName== "")
            fileName = "coffee-default.png";
        else
            fileName = "coffee"+sanpham.getId().toString()+".png";
        String uploadDir = "src/main/resources/static/images/products/";
        saveFile(uploadDir, fileName, file);

        sanpham.setAnh("/images/products/"+fileName);
        sanPhamService.saveProduct(sanpham);
        return  "redirect:/admin/quan-ly-san-pham";

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id , Model model){
        model.addAttribute("sanpham", sanPhamService.getProductByID(id));
        model.addAttribute("dsloaisp", loaiSanPhamService.getAllCategories());
        model.addAttribute("dsthuonghieu", thuongHieuService.getAllThuongHieu());
        return "admin/quanlysanpham/edit";
    }
    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute("sanpham") Sanpham sanpham){
        if(sanpham.getAnh()== null)
            sanpham.setAnh("coffee-default.png");
        sanPhamService.saveProduct(sanpham);
        return "redirect:/admin/quan-ly-san-pham";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        sanPhamService.deleteProductByID(id);
        return "redirect:/admin/quan-ly-san-pham";
    }

    @GetMapping("/uploadimage") public String displayUploadForm() {
        return "admin/quanlysanpham/up";
    }

    @PostMapping("/upload")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file) throws IOException {
        Sanpham sanpham= sanPhamService.getProductByID(5L);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName== ""){
            return "redirect:/admin/quanlysanpham";
        }
    //    String fileName = "cafe"+sanpham.getId().toString()+".png";
        String uploadDir = "src/main/resources/static/images/products/";
        saveFile(uploadDir, fileName, file);


        sanpham.setAnh(uploadDir+fileName);
        sanPhamService.saveProduct(sanpham);
        return "redirect:/admin/quanlysanpham/up";
    }
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
    @GetMapping("/chitietsp/{id}")
    public String ChiTietSP(@PathVariable Long id, Model model){
        model.addAttribute("sanpham", sanPhamService.getProductByID(id));
        return "product/chitietsp";
    }
}
