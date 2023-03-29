package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thayduc.quanlydancu.demo.dto.HoaDonDTO;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.service.HoaDonService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HoaDonApi {
    @Autowired
    private HoaDonService hoaDonService;
    @PostMapping("/gui-hoa-don")
    public ResponseEntity<List<HoaDonDTO>> sendUser(@RequestBody  Map<String,String> params){
        String username = params.get("userNameSend");
        System.out.println(username);
        List<HoaDonDTO> list = hoaDonService.findAllHoaDonByUser(username);
        if(list != null){
            return  new ResponseEntity<>(list, HttpStatus.OK);

        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
