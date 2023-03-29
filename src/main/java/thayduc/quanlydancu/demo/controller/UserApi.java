package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import thayduc.quanlydancu.demo.dto.UserDTO;
import thayduc.quanlydancu.demo.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserApi {
    @Autowired
    private UserService userService;
    @PostMapping("/tim-kiem-dan-cu")
    public ResponseEntity<List<UserDTO>> getAllUser(@RequestBody Map<String, String> params){

        String nameSearch = String.valueOf(params.get("nameSearch")+"%");
        List<UserDTO> list = userService.findAllUser(nameSearch);
        if(list != null){
            return  new ResponseEntity<>(list, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
