package thayduc.quanlydancu.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import thayduc.quanlydancu.demo.dto.CommentDTO;
import thayduc.quanlydancu.demo.entity.BaiViet;
import thayduc.quanlydancu.demo.entity.Comment;
import thayduc.quanlydancu.demo.entity.ServiceRespone;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.repository.BaiVietRepository;
import thayduc.quanlydancu.demo.service.BaiVietService;
import thayduc.quanlydancu.demo.service.CommentService;
import thayduc.quanlydancu.demo.service.UserService;
import thayduc.quanlydancu.demo.service.serviceIpm.BaiVietServiceIpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class CommentApi {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @PostMapping(value = "/comment", produces = "application/json")
    public ResponseEntity<List<CommentDTO>> addComment(@RequestBody Map<String, String> params, Authentication authentication){
       User user = (User) authentication.getPrincipal();
        String content = params.get("content");
        Long idBaiViet = Long.parseLong(params.get("idBaiViet"));
        if(content !=null && idBaiViet!=null){
        this.commentService.addComment( idBaiViet, content, user);
        List<CommentDTO> list = commentService.findAllComment(idBaiViet);
            return new ResponseEntity<List<CommentDTO>>(list, HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
