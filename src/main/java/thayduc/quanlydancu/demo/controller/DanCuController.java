package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thayduc.quanlydancu.demo.entity.CuDan;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.excel.DanCuExcelExporter;
import thayduc.quanlydancu.demo.service.QuanLyCuDanService;
import thayduc.quanlydancu.demo.service.serviceIpm.QuanLyCuDanServiceIpm;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(value = "/cu-dan")
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class DanCuController {
    @Autowired
    private QuanLyCuDanServiceIpm cuDanService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @GetMapping("/them-cu-dan")
    public String addCuDan(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        CuDan cuDan = new CuDan();
        model.addAttribute("user",user);
        model.addAttribute("cuDan", cuDan);
        return "QuanLyCuDan/themCuDan";
    }
    @PostMapping("/them-cu-dan")
    public String addCuDans(Authentication authentication, Model model, @ModelAttribute("cuDan") @Valid CuDan cuDan, BindingResult bindingResult, RedirectAttributes redirect) {
        User user = (User) authentication.getPrincipal();
        Date date = new Date();
        model.addAttribute("user",user);
        if(bindingResult.hasErrors()){
            return "QuanLyCuDan/themCuDan";
        }
        else {
            cuDanService.addCuDan(cuDan);
            List<CuDan> list = cuDanService.findByAll();
            model.addAttribute("listCuDan",list);
            return "QuanLyCuDan/danhSachCuDan";
        }

    }
    @GetMapping("/danh-sach-cu-dan")
    public String update(Model model, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        List<CuDan> list = cuDanService.findByAll();
        model.addAttribute("listCuDan",list);
        model.addAttribute("user",user);
        return "QuanLyCuDan/danhSachCuDan";
    }
    @PostMapping("/cap-nhat")
    public String doUpdate(@ModelAttribute("cuDan")  @Valid  CuDan cuDan,BindingResult bindingResult, Model model, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        model.addAttribute("user",user);
        if(bindingResult.hasErrors()){
            return "QuanLyCuDan/suaCuDan";
        }
        else {
            cuDanService.addCuDan(cuDan);
            List<CuDan> list = cuDanService.findByAll();
            model.addAttribute("listCuDan",list);
            return "QuanLyCuDan/danhSachCuDan";
        }

    }
    @GetMapping("/cap-nhat")
    public String updateTest( @ModelAttribute("id") Long id, Model model, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        model.addAttribute("user",user);
        Optional<CuDan> optionalCuDan = cuDanService.findById(id);
        if (optionalCuDan.isPresent()) {
            model.addAttribute("cuDan", optionalCuDan.get());

        }

        return "QuanLyCuDan/suaCuDan";
    }

    @GetMapping("/xoa")
    public String deleteDV(@ModelAttribute("id") Long id, Authentication authentication, Model model){
        User user  = (User) authentication.getPrincipal();
        cuDanService.deleteById(id);
        List<CuDan> list = cuDanService.findByAll();
        model.addAttribute("listCuDan",list);
        model.addAttribute("user",user);
        return "QuanLyCuDan/danhSachCuDan";
    }

    @GetMapping("/xuat-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<CuDan> cuDanList = cuDanService.findByAll();
        DanCuExcelExporter excelExporter = new DanCuExcelExporter(cuDanList);
        excelExporter.export(response);
    }


}
