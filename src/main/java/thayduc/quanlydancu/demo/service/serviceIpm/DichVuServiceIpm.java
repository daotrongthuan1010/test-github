package thayduc.quanlydancu.demo.service.serviceIpm;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.dto.DichVuDTO;
import thayduc.quanlydancu.demo.entity.DichVu;
import thayduc.quanlydancu.demo.repository.DichVuRepository;
import thayduc.quanlydancu.demo.service.DichVuService;
import thayduc.quanlydancu.demo.utility.ChuanHoa;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class DichVuServiceIpm implements DichVuService  {

    @Autowired
    private DichVuRepository dichVuRepository;


    @Override
    public void save(DichVu dichVu) {
        ChuanHoa chuanHoa = new ChuanHoa();
        String tenDichVu = chuanHoa.chuanHoaToanBo(dichVu.getTendv());
        dichVu.setTendv(tenDichVu);
        dichVuRepository.save(dichVu);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dichVuRepository.deleteById(id);
    }



    @Override
    public List<DichVu> findByAll() {
        List<DichVu> list = dichVuRepository.findAll();
        return list;
    }

    @Override
    public Optional<DichVu> findById(Long id) {
        Optional<DichVu> optionalDichVu = dichVuRepository.findById(id);
        return optionalDichVu;
    }

    @Override
    public List<DichVuDTO> findByName(String name)  {

        List<Map<String, Object>>  list=  dichVuRepository.findByName(name);
        List<DichVuDTO> dichVus = new ArrayList<>();
        list.forEach(
                x->{
                    DichVuDTO dichVuDTO = new DichVuDTO();
                    dichVuDTO.setDonViTinh(String.valueOf(x.get("don_vi_tinh")));
                    dichVuDTO.setGiaTien(String.valueOf(x.get("giatien")));
                    dichVus.add(dichVuDTO);
                }
        );
        System.out.println(dichVus);

           return dichVus;


    }

    @Override
    public DichVuDTO findByIdName(Long id) {

        Map<String, Object> x = dichVuRepository.findByIds(id);
        DichVuDTO dichVuDTO = new DichVuDTO();
        dichVuDTO.setDonViTinh(String.valueOf(x.get("don_vi_tinh")));
        dichVuDTO.setGiaTien(String.valueOf(x.get("giatien")));
        return dichVuDTO;
    }


}
