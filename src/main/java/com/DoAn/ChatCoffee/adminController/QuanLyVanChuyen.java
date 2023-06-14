package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Vanchuyen;
import com.DoAn.ChatCoffee.service.QuanLyVanChuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/quan-ly-van-chuyen")
public class QuanLyVanChuyen {
    @Autowired
    private QuanLyVanChuyenService quanLyVanChuyenService;

    @GetMapping("/page/{pageNo}")
    public String page(Model model, @PathVariable(value = "pageNo") int pageNo){
        int pageSize= 6;
        Page<Vanchuyen> page=quanLyVanChuyenService.findPaginated(pageNo, pageSize);
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
        List<Vanchuyen> listvc= page.getContent();
        model.addAttribute("listvc", listvc);
        return "admin/QuanLyVanChuyen/index";
    }

    @GetMapping()
    public String getAllVanChuyen(Model model) {
        //model.addAttribute("listvc", quanLyVanChuyenService.getAllVanChuyen());
        return page(model, 1);
    }

    @GetMapping("/add")
    public String addNewForm(Model model) {
        model.addAttribute("vanchuyen", new Vanchuyen());
        return "admin/QuanLyVanChuyen/add";
    }

    @PostMapping("/add")
    public String addsubmit(@ModelAttribute("vanchuyen") Vanchuyen vanchuyen) {
        vanchuyen.setGia((Long) vanchuyen.getGia());
        quanLyVanChuyenService.save(vanchuyen);
        return "redirect:/admin/quan-ly-van-chuyen";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id ){
        quanLyVanChuyenService.delete(id);
        return "redirect:/admin/quan-ly-van-chuyen";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("vanchuyen", quanLyVanChuyenService.getVanChuyenById(id));
        return "admin/QuanLyVanChuyen/edit";
    }
    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute("vanchuyen") Vanchuyen vanchuyen){
        quanLyVanChuyenService.save(vanchuyen);
        return "redirect:/admin/quan-ly-van-chuyen";
    }
}
