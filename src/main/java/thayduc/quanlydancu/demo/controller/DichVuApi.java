package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import thayduc.quanlydancu.demo.dto.DichVuDTO;
import thayduc.quanlydancu.demo.entity.DichVu;
import thayduc.quanlydancu.demo.service.DichVuService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DichVuApi {
    @Autowired
    private DichVuService dichVuService;
    @PostMapping(value = "/tim-kiem-dich-vu",produces = "application/json")
    public ResponseEntity<DichVuDTO> searchDichVus(@RequestBody Map<String,String> prams){
        Long value = Long.parseLong(prams.get("id"));
        DichVuDTO dichVuDTO = dichVuService.findByIdName(value);
        System.out.println(dichVuDTO);
        if(dichVuDTO !=null){
            return new ResponseEntity<>(dichVuDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
