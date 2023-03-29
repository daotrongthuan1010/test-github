package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import thayduc.quanlydancu.demo.entity.CanHo;
import thayduc.quanlydancu.demo.entity.CuDan;
import thayduc.quanlydancu.demo.entity.CuDanCanHo;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.service.QuanLyCuDanService;
import thayduc.quanlydancu.demo.service.serviceIpm.CanHoServiceIpm;
import thayduc.quanlydancu.demo.service.serviceIpm.QuanLyCuDanCanHoServiceIpm;
import thayduc.quanlydancu.demo.service.serviceIpm.QuanLyCuDanServiceIpm;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class QuanLyCuDanCanHoController {
//    @Autowired
//    private QuanLyCuDanCanHoServiceIpm cuDanCanHoServiceIpm;
//    @Autowired
//    private QuanLyCuDanServiceIpm quanLyCuDanServiceIpm;
//    @Autowired
//    private CanHoServiceIpm  canHoServiceIpm;
//
//    @GetMapping("/themcudancanho")
//    public String addCuDan(Authentication authentication, Model model) {
//        User user = (User) authentication.getPrincipal();
//        CuDanCanHo cuDanCanHo = new CuDanCanHo();
//        model.addAttribute("user",user);
//        model.addAttribute("cuDanCanHo", cuDanCanHo);
//        return "QuanLyCuDan/ThemCuDan";
//    }
//    @PostMapping("/themcudancanho")
//    public String addDichVus(Authentication authentication, Model model, @ModelAttribute("cuDanCanHo") CuDanCanHo cuDanCanHo, @RequestParam("ten") String ten, @RequestParam("cmt") String cmt) {
//        User user = (User) authentication.getPrincipal();
//        Date date = new Date();
//        Optional<CuDan> optionalCuDan = quanLyCuDanServiceIpm.findBycmt(cmt);
//        Optional<CanHo> optionalCanHo = canHoServiceIpm.findByName(ten);
//        cuDanCanHo.setNgayDen(date);
//        cuDanCanHo.setCuDan(optionalCuDan.get());
//        cuDanCanHo.setCanHo(optionalCanHo.get());
//        model.addAttribute("user",user);
//        cuDanCanHoServiceIpm.addCuDanCanHo(cuDanCanHo);
//        List<CuDanCanHo> list = cuDanCanHoServiceIpm.findByAll();
//        model.addAttribute("listCuDanCanHo",list);
//        return "QuanLyCuDan/DanhSachCuDan";
//
//
//    }
//}
}