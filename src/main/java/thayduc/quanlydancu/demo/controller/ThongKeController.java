package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thayduc.quanlydancu.demo.dto.HoaDonTKDTO;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.service.HoaDonService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeController {
    @Autowired
    private HoaDonService hoaDonService;
    @GetMapping("/dich-vu")
    public String getTK(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "ThongKe/dichVuTheoHoaDonBD";
    }
    @PostMapping("/dich-vu")
    public String getTKDichVu(Model model, Authentication authentication, @RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate ) throws  Exception{
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        List<HoaDonTKDTO> list = hoaDonService.thongKeDichVuByHoaDon2(date1,date2);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        model.addAttribute("listHoaDon", list);
        return "ThongKe/dichVuTheoHoaDon";
    }

}
