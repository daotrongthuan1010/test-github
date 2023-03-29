package thayduc.quanlydancu.demo.service.serviceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.dto.BaiVietDTO;
import thayduc.quanlydancu.demo.entity.BaiViet;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.repository.BaiVietRepository;
import thayduc.quanlydancu.demo.repository.UserRepository;
import thayduc.quanlydancu.demo.service.BaiVietService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class BaiVietServiceIpm implements BaiVietService {
    @Autowired
    private BaiVietRepository baiVietRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public BaiViet addBaiViet(String noiDung,String img, String username) {
            User user =userRepository.findByUsername(username);
            if(user != null  && noiDung !=null){
                BaiViet baiViet = new BaiViet();
                baiViet.setUser(user);
                baiViet.setNoiDung(noiDung);
                baiViet.setImage(img);
                return baiVietRepository.save(baiViet);

            }
            return  null;

    }

    @Override
    public List<BaiViet> findAll() {
        List<BaiViet> list = baiVietRepository.findAll();
        return list;
    }


    @Override
    public List<BaiVietDTO> findAlls() {

        List<BaiVietDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj = baiVietRepository.findUserByBaiViet();
        obj.forEach(x->
        {  BaiVietDTO baiVietDTO = new BaiVietDTO();
            baiVietDTO.setId(String.valueOf(x.get("id")));
            baiVietDTO.setHoTen(String.valueOf(x.get("ho_ten")));
            baiVietDTO.setImageAvatar(String.valueOf(x.get("img_avatar")));
            baiVietDTO.setLastModifiedDate(String.valueOf(x.get("last_modified_date")));
            baiVietDTO.setNoiDung(String.valueOf(x.get("noi_dung")));
            baiVietDTO.setImage(String.valueOf(x.get("image")));
            list.add(baiVietDTO);

        });
        return  list;

    }

    @Override
    @Transactional
    public Optional<BaiViet> findById(Long id) {
        Optional<BaiViet> baiViet = baiVietRepository.findById(id);
        return baiViet;
    }


}
