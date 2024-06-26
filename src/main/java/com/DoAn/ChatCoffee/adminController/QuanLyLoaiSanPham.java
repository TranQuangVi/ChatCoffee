package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Loaisanpham;
import com.DoAn.ChatCoffee.service.LoaiSanPhamService;
import com.DoAn.ChatCoffee.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/QuanLyLoaiSanPham")
public class QuanLyLoaiSanPham {
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/page/{pageNo}")
    public String page(Model model, @PathVariable(value = "pageNo") int pageNo){
        int pageSize= 3;
        Page<Loaisanpham> page=loaiSanPhamService.findPaginated(pageNo, pageSize);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        int totalPages = page.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        List<Loaisanpham> Loais= page.getContent();
        model.addAttribute("Loais", Loais);
        return "admin/QuanLyLoaiSanPham/index";
    }

    @GetMapping()
    public String index(Model model){
        return page(model, 1);
    }

    @GetMapping("/addCategory")
    public String addCategory(Model model){

        model.addAttribute("loai",new Loaisanpham());
        return "admin/QuanLyLoaiSanPham/addCategory";
    }
    @PostMapping("/addCategory")
    public String addLoai(@ModelAttribute("loai") Loaisanpham loaisanpham){
        loaiSanPhamService.addLoaiSP(loaisanpham);
        return "redirect:/admin/QuanLyLoaiSanPham";
    }
    @GetMapping("/edit/{id}")
    public  String editBookForm(@PathVariable Long id, Model model){
        model.addAttribute("loai",loaiSanPhamService.getloaiSanphamByid(id));
        return "admin/QuanLyLoaiSanPham/edit";

    }
    @PostMapping("/edit{id}")
    public String editBook(@ModelAttribute("loai") Loaisanpham loai){
        loaiSanPhamService.saveLoaiSP(loai);
        return "redirect:/admin/QuanLyLoaiSanPham";
    }
    @GetMapping("/deleteCategory/{id}")
    public String deleteLoaiSP(@PathVariable("id") Long id){
        Loaisanpham book = loaiSanPhamService.getloaiSanphamByid(id);
        loaiSanPhamService.deleteLoaiSP(id);
        return "redirect:/admin/QuanLyLoaiSanPham";

    }
}
