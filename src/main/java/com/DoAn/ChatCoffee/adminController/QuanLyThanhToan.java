package com.DoAn.ChatCoffee.adminController;

import com.DoAn.ChatCoffee.entity.Sanpham;
import com.DoAn.ChatCoffee.entity.Thanhtoan;
import com.DoAn.ChatCoffee.entity.Vanchuyen;
import com.DoAn.ChatCoffee.service.QuanLyThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/quan-ly-thanh-toan")
public class QuanLyThanhToan {
    @Autowired
    private QuanLyThanhToanService quanLyThanhToanService;

    @GetMapping("/page/{pageNo}")
    public String page(Model model, @PathVariable(value = "pageNo") int pageNo){
        int pageSize= 6;
        Page<Thanhtoan> page=quanLyThanhToanService.findPaginated(pageNo, pageSize);
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
        List<Thanhtoan> listThanhToan= page.getContent();
        model.addAttribute("listThanhToan", listThanhToan);
        return "admin/quanlythanhtoan/index";
    }

    @GetMapping()
    public String getAllThanhtoan(Model model) {
        //model.addAttribute("listThanhToan", quanLyThanhToanService.getAllThanhToan());
        return page(model, 1);
    }

    @GetMapping("/add")
    public String addNewForm(Model model) {
        model.addAttribute("thanhtoan", new Thanhtoan());
        return "admin/QuanLyThanhToan/add";
    }

    @PostMapping("/add")
    public String addsubmit(@ModelAttribute("thanhtoan") Thanhtoan thanhtoan) {

        quanLyThanhToanService.save(thanhtoan);
        return "redirect:/admin/quan-ly-thanh-toan";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id ){
        quanLyThanhToanService.delete(id);
        return "redirect:/admin/quan-ly-thanh-toan";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("thanhtoan", quanLyThanhToanService.getThanhToanById(id));
        return "admin/QuanLyThanhToan/edit";
    }
    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute("thanhtoan") Thanhtoan thanhtoan){
        quanLyThanhToanService.save(thanhtoan);
        return "redirect:/admin/quan-ly-thanh-toan";
    }
}
