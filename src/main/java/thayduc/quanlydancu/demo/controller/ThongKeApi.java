package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import thayduc.quanlydancu.demo.dto.HoaDonTKDTO;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.service.HoaDonService;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ThongKeApi {
    @Autowired
    private HoaDonService hoaDonService;


//    @PostMapping(value = "/thong-ke/dich-vu-hoa-don", produces = "application/json")
//    public ResponseEntity<List<HoaDonTKDTO>> timeLineSupplierPost(@RequestBody Map<String, LocalDateTime> params) throws Exception {
//        LocalDateTime date1 = params.get("startDate");
//        LocalDateTime date2 = params.get("endDate");
//        List<HoaDonTKDTO> list = hoaDonService.thongKeDichVuByHoaDon(date1, date2);
//        if(list != null){
//            return  new ResponseEntity<>(list, HttpStatus.OK);
//
//    }
//        else {
//            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//  }


}
