package thayduc.quanlydancu.demo.service;

import org.springframework.stereotype.Service;
import thayduc.quanlydancu.demo.dto.DichVuDTO;
import thayduc.quanlydancu.demo.entity.DichVu;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DichVuService {
    void save(DichVu dichVu);
    void deleteById(Long id);
    List<DichVu> findByAll();
    Optional<DichVu> findById(Long id);
   List<DichVuDTO> findByName(String name);
    DichVuDTO findByIdName(Long id);

}
