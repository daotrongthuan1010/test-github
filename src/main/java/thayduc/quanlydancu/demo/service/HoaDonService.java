package thayduc.quanlydancu.demo.service;

import thayduc.quanlydancu.demo.dto.HoaDonDTO;
import thayduc.quanlydancu.demo.dto.HoaDonTKDTO;
import thayduc.quanlydancu.demo.entity.HoaDon;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HoaDonService {
    HoaDon luuHoaDon(HoaDon hoaDon);
    void xoaHoaDon(Long id);
    Optional<HoaDon> findById(Long id);
    List<HoaDon> findAll();
    List<HoaDonDTO> findAllHoaDon();
    List<HoaDonDTO> findAllHoaDonByUser(String username);
    List<HoaDonTKDTO> thongKeDichVuByHoaDon(LocalDateTime startDate, LocalDateTime endDate);
    List<HoaDonTKDTO> thongKeDichVuByHoaDon2(Date startDate, Date endDate);

}
