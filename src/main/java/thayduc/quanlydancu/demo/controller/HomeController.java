package thayduc.quanlydancu.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import thayduc.quanlydancu.demo.dto.BaiVietDTO;
import thayduc.quanlydancu.demo.entity.BaiViet;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.service.BaiVietService;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private BaiVietService baiVietService;

    /**
     *
     * @return view trang chu
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/trang-chu-admin")
    public String trangChuAdmin(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "ThemDichVu";
    }
    @GetMapping("/thong-bao-cu-dan")
    public String thongBao(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<BaiVietDTO> list = baiVietService.findAlls();
        model.addAttribute("user", user);
        model.addAttribute("list", list);
        System.out.println(list);
        return "ThongBao/thongBao";
    }



}
