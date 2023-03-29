package thayduc.quanlydancu.demo.service;

import thayduc.quanlydancu.demo.dto.BaiVietDTO;
import thayduc.quanlydancu.demo.entity.BaiViet;

import java.util.List;
import java.util.Optional;

public interface BaiVietService {
    BaiViet addBaiViet(String noiDung,String img, String username);
    List<BaiViet> findAll();
    List<BaiVietDTO> findAlls();
    Optional<BaiViet> findById(Long id);

}
