package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Thuonghieu;
import com.DoAn.ChatCoffee.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin/QuanLyThuongHieu")
public class QuanLyThuongHieu {
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("/page/{pageNo}")
    public String page(Model model, @PathVariable(value = "pageNo") int pageNo){
        int pageSize= 2;
        Page<Thuonghieu> page=thuongHieuService.findPaginated(pageNo, pageSize);
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
        List<Thuonghieu> ListBrands= page.getContent();
        model.addAttribute("ListBrands", ListBrands);
        return "admin/QuanLyThuongHieu/index";
    }

    @GetMapping
    public String index(Model model){
        //model.addAttribute("ListBrands",thuongHieuService.getAllThuongHieu());
        return page(model, 1);
    }

    @GetMapping("/deleteBrand/{id}")
    public String deleteBrand(@PathVariable("id") Long id){
        Thuonghieu thuonghieu= thuongHieuService.getThuongHieuById(id);
        thuongHieuService.deleteThuongHieu(thuonghieu.getMaTH());
        return "redirect:/admin/QuanLyThuongHieu";
    }

    @GetMapping("/editBrand/{id}")
    public String editBrandForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("brand",thuongHieuService.getThuongHieuById(id));
        return "admin/QuanLyThuongHieu/editBrand";
    }
    @PostMapping("/editBrand")
    public String editBrand(@ModelAttribute("brand") Thuonghieu updatebrand){
        thuongHieuService.saveThuongHieu(updatebrand);
        return "redirect:/admin/QuanLyThuongHieu";
    }

    @GetMapping("/addBrand")
    public String addBrandFrom(Model model){
        model.addAttribute("brand", new Thuonghieu());
        return "admin/QuanLyThuongHieu/addBrand";
    }
    @PostMapping("/addBrand")
    public String addBrand(@ModelAttribute("brand") Thuonghieu thuonghieu){
        thuongHieuService.saveThuongHieu(thuonghieu);
        return "redirect:/admin/QuanLyThuongHieu";
    }
}
