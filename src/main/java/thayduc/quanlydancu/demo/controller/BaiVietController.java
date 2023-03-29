package thayduc.quanlydancu.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import thayduc.quanlydancu.demo.entity.BaiViet;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.service.BaiVietService;
import thayduc.quanlydancu.demo.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/quan-ly")
public class BaiVietController {
    @Autowired
    private BaiVietService baiVietService;
    @Autowired
    private UserService userService;
    @Autowired
    private Cloudinary cloudinary;
    @GetMapping("/dang-bai")
    public String addBaiViets(Model model, Authentication authentication ){
        User user  = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        BaiViet baiViet = new BaiViet();
        List<BaiViet> list = baiVietService.findAll();
        model.addAttribute("list",list);
        model.addAttribute("baiViet",baiViet);
        return "ThongBao/dangBai";

    }



    @PostMapping(value = "/dang-bai")
    public String addBaiViet(@ModelAttribute("baiViet") BaiViet baiViet,
                             @RequestParam("noiDung") String noiDung,
                             @RequestParam("file") MultipartFile file,
                             Authentication authentication, Model model){
        User user  = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        String username = user.getUsername();
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String img =(String) uploadResult.get("secure_url");
            baiViet = this.baiVietService.addBaiViet(noiDung, img,  username);

            }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        List<BaiViet> list = baiVietService.findAll();
        list.forEach(abc -> {
            System.out.println();
        });

        list.forEach(BaiViet::getComments);
        model.addAttribute("list",list);

        return "ThongBao/dangBai";

    }


}
