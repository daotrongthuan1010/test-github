package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thayduc.quanlydancu.demo.entity.CuDan;
import thayduc.quanlydancu.demo.service.serviceIpm.QuanLyCuDanServiceIpm;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CuDanApi {
     @Autowired
    private QuanLyCuDanServiceIpm quanLyCuDanServiceIpm;
    @GetMapping("/cu-dan/get-all")
     public List<CuDan> getAll(){
        List<CuDan> list = quanLyCuDanServiceIpm.findByAll();
        return  list;
    }

}
