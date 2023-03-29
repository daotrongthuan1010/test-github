package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thayduc.quanlydancu.demo.entity.DichVu;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.service.DichVuService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dich-vu")
public class DichVuController {

    @Autowired
    private DichVuService dichVuService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @GetMapping("/them-dich-vu")
    public String addDichVu(Authentication authentication, Model model) {
       User user = (User) authentication.getPrincipal();
        DichVu dichVu = new DichVu();
        model.addAttribute("user",user);
        model.addAttribute("dichVu", dichVu);
        return "QuanLyDichVu/themDichVu";
    }
    @PostMapping("/them-dich-vu")
    public String addDichVus(Authentication authentication,Model model, @ModelAttribute("dichVu") DichVu dichVu) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user",user);
        dichVuService.save(dichVu);
        List<DichVu> list = dichVuService.findByAll();
        model.addAttribute("listDichVu",list);
        return "QuanLyDichVu/danhSachDichVu";
    }
    @GetMapping("/danh-sach-dich-vu")
    public String update(Model model, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        List<DichVu> list = dichVuService.findByAll();
        model.addAttribute("listDichVu",list);
        model.addAttribute("user",user);
        return "QuanLyDichVu/danhSachDichVu";
    }
    @GetMapping("/cap-nhat")
    public String update( @ModelAttribute("id") Long id, Model model, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        model.addAttribute("user",user);
        Optional<DichVu> dichVu = dichVuService.findById(id);
        if (dichVu.isPresent()) {
            model.addAttribute("dichVu", dichVu.get());

        }

        return "QuanLyDichVu/suaDichVu";
    }

    @PostMapping("/cap-nhat")
    public String doUpdate(Model model, @ModelAttribute("dichVu") DichVu dichVu, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        Date date = new Date();
        dichVuService.save(dichVu);
        List<DichVu> list = dichVuService.findByAll();
        model.addAttribute("listDichVu",list);
        model.addAttribute("user",user);
        return "QuanLyDichVu/danhSachDichVu";
    }
    @GetMapping("/xoa")
    public String deleteDV(@ModelAttribute("id") Long id, Authentication authentication, Model model){
        User user  = (User) authentication.getPrincipal();
        dichVuService.deleteById(id);
        List<DichVu> list = dichVuService.findByAll();
        model.addAttribute("listDichVu",list);
        model.addAttribute("user",user);
        return "QuanLyDichVu/danhSachDichVu";
    }
}
