package thayduc.quanlydancu.demo.service.serviceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.dto.CommentDTO;
import thayduc.quanlydancu.demo.dto.HoaDonDTO;
import thayduc.quanlydancu.demo.dto.HoaDonTKDTO;
import thayduc.quanlydancu.demo.entity.HoaDon;
import thayduc.quanlydancu.demo.repository.HoaDonRepository;
import thayduc.quanlydancu.demo.service.HoaDonService;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class HoaDonServiceIpm implements HoaDonService {
    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    @Transactional
    public HoaDon luuHoaDon(HoaDon hoaDon) {
        return hoaDonRepository.save(hoaDon);
    }

    @Override
    @Transactional
    public void xoaHoaDon(Long id) {
        hoaDonRepository.deleteById(id);
    }

    @Override
    public Optional<HoaDon> findById(Long id) {
        return hoaDonRepository.findById(id);
    }

    @Override
    public List<HoaDon> findAll() {
        List<HoaDon> list = hoaDonRepository.findAll();
        return  list;
    }

    @Override
    public List<HoaDonDTO> findAllHoaDon() {
        List<HoaDonDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj = hoaDonRepository.findAllHoaDon();
        obj.forEach(x ->
        {
           HoaDonDTO hoaDonDTO = new HoaDonDTO();
            hoaDonDTO.setId(String.valueOf(x.get("id")));
            hoaDonDTO.setHoTen(String.valueOf(x.get("ho_ten")));
            hoaDonDTO.setEmail(String.valueOf(x.get("email")));
            hoaDonDTO.setTendv(String.valueOf(x.get("tendv")));
            hoaDonDTO.setDonViTinh(String.valueOf(x.get("don_vi_tinh")));
            hoaDonDTO.setGiaTien(String.valueOf(x.get("giatien")));
            hoaDonDTO.setSoDaDung(String.valueOf(x.get("so_da_dung")));
            hoaDonDTO.setTongTien(String.valueOf(x.get("tong_tien")));
            hoaDonDTO.setNgayTao(String.valueOf(x.get("created_date")));
            list.add(hoaDonDTO);

        });

        return list;
    }
    public List<HoaDonDTO> findAllHoaDonByUser(String username){
        List<HoaDonDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj = hoaDonRepository.findHoaDonByUser(username);
        obj.forEach(x ->
        {
            HoaDonDTO hoaDonDTO = new HoaDonDTO();
            hoaDonDTO.setId(String.valueOf(x.get("id")));
            hoaDonDTO.setHoTen(String.valueOf(x.get("ho_ten")));
            hoaDonDTO.setEmail(String.valueOf(x.get("email")));
            hoaDonDTO.setTendv(String.valueOf(x.get("tendv")));
            hoaDonDTO.setDonViTinh(String.valueOf(x.get("don_vi_tinh")));
            hoaDonDTO.setGiaTien(String.valueOf(x.get("giatien")));
            hoaDonDTO.setSoDaDung(String.valueOf(x.get("so_da_dung")));
            hoaDonDTO.setTongTien(String.valueOf(x.get("tong_tien")));
            hoaDonDTO.setNgayTao(String.valueOf(x.get("created_date")));
            list.add(hoaDonDTO);

        });

        return list;

    }

    @Override
    public List<HoaDonTKDTO> thongKeDichVuByHoaDon(LocalDateTime startDate, LocalDateTime endDate) {

        List<HoaDonTKDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj = hoaDonRepository.getHoaDonByDichVu2(startDate, endDate);
        obj.forEach(x ->
        {
            HoaDonTKDTO hoaDonTKDTO = new HoaDonTKDTO();
            hoaDonTKDTO.setId(String.valueOf(x.get("id")));
            hoaDonTKDTO.setTenDichVu(String.valueOf(x.get("tendv")));
            hoaDonTKDTO.setTongTien(String.valueOf(x.get("tongtienDV")));
            list.add(hoaDonTKDTO);
        });
        System.out.println(list);
        return  list;
    }

    @Override
    public List<HoaDonTKDTO> thongKeDichVuByHoaDon2(Date startDate, Date endDate) {

        List<HoaDonTKDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj = hoaDonRepository.getHoaDonByDichVu(startDate, endDate);
        obj.forEach(x ->
        {
            HoaDonTKDTO hoaDonTKDTO = new HoaDonTKDTO();
            hoaDonTKDTO.setId(String.valueOf(x.get("id")));
            hoaDonTKDTO.setTenDichVu(String.valueOf(x.get("tendv")));
            hoaDonTKDTO.setTongTien(String.valueOf(x.get("tongtienDV")));
            list.add(hoaDonTKDTO);
        });
        System.out.println(list);
        return  list;
    }


}
