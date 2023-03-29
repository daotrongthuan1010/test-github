package thayduc.quanlydancu.demo.controller;

import org.apache.http.HttpRequest;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import thayduc.quanlydancu.demo.dto.HoaDonDTO;
import thayduc.quanlydancu.demo.entity.CuDan;
import thayduc.quanlydancu.demo.entity.DichVu;
import thayduc.quanlydancu.demo.entity.HoaDon;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.excel.DanCuExcelExporter;
import thayduc.quanlydancu.demo.excel.HoaDonExportExcel;
import thayduc.quanlydancu.demo.service.DichVuService;
import thayduc.quanlydancu.demo.service.HoaDonService;
import thayduc.quanlydancu.demo.service.UserService;
import thayduc.quanlydancu.demo.service.serviceIpm.HoaDonServiceIpm;
import thayduc.quanlydancu.demo.service.serviceIpm.QuanLyCuDanServiceIpm;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private DichVuService dichVuService;
    @Autowired
    private UserService userService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @GetMapping("/them-hoa-don")
    public String addCuDan(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        List<DichVu> list = dichVuService.findByAll();
        HoaDon hoaDon = new HoaDon();
        model.addAttribute("user",user);
        model.addAttribute("list", list);
        model.addAttribute("hoaDon", hoaDon);
        return "HoaDon/themHoaDon";
    }
    @PostMapping("/them-hoa-don")
    public String addDichVus(Authentication authentication,
                             Model model,
                             @Valid @ModelAttribute("hoaDon") HoaDon hoaDon,
                             @RequestParam("soDaDung") Long soDaDung,
                             @RequestParam("dichVuById") Long dichVuById,
                             @RequestParam("idUserSearch") Long idUserSearch,
                              BindingResult bindingResult) {
        User user = (User) authentication.getPrincipal();
        Optional<User> userSuDung = userService.findById(idUserSearch);
        Optional<DichVu> dichVu = dichVuService.findById(dichVuById);
        model.addAttribute("user",user);
        if(bindingResult.hasErrors()){
            return "HoaDon/themHoaDon";
        }
        else {

            hoaDon.setUser(userSuDung.get());
            hoaDon.setSoDaDung(soDaDung);
            hoaDon.setDichVu(dichVu.get());
            BigDecimal bigDecimal = new BigDecimal( soDaDung*dichVu.get().getGiatien());
            hoaDon.setTongTien(bigDecimal);
            hoaDonService.luuHoaDon(hoaDon);
            List<HoaDonDTO> list = hoaDonService.findAllHoaDon();
            model.addAttribute("listHoaDon",list);
            return "HoaDon/danhSachHoaDon";
        }



    }
    @GetMapping("/danh-sach-hoa-don")
    public String update(Model model, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        List<HoaDonDTO> list = hoaDonService.findAllHoaDon();
        model.addAttribute("listHoaDon",list);
        model.addAttribute("user",user);
        return "danhSachHoaDon";
    }
    @GetMapping("/cap-nhat")
    public String update( @ModelAttribute("id") Long id, Model model, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        model.addAttribute("user",user);
        Optional<HoaDon> optionalHoaDon = hoaDonService.findById(id);
        if (optionalHoaDon.isPresent()) {
            model.addAttribute("hoaDon", optionalHoaDon.get());

        }

        return "HoaDon/suaHoaDon";
    }

    @PostMapping("/cap-nhat")
    public String doUpdate(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon, Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        model.addAttribute("user",user);
        hoaDonService.luuHoaDon(hoaDon);
        List<HoaDonDTO> list = hoaDonService.findAllHoaDon();
        model.addAttribute("listHoaDon",list);

        return "HoaDon/danhSachHoaDon";
    }
    @GetMapping("/xoa")
    public String deleteDV(@ModelAttribute("id") Long id, Authentication authentication, Model model){
        User user  = (User) authentication.getPrincipal();
//        hoaDonService.deleteBy(id);
//        List<CuDan> list = cuDanService.findByAll();
//        model.addAttribute("listCuDan",list);
        model.addAttribute("user",user);
        return "danhSachCuDan";
    }

    @GetMapping("/xuat-excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=danhsach_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<HoaDonDTO> hoaDonDTOList = hoaDonService.findAllHoaDon();
        HoaDonExportExcel excelExporter = new HoaDonExportExcel(hoaDonDTOList);
        excelExporter.export(response);
    }

}
