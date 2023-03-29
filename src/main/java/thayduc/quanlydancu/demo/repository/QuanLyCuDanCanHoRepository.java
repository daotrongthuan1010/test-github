package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import thayduc.quanlydancu.demo.dto.CuDanCanHoDTO;
import thayduc.quanlydancu.demo.entity.CuDanCanHo;

import java.util.Date;
import java.util.List;

@Repository
public interface QuanLyCuDanCanHoRepository extends JpaRepository<CuDanCanHo, Long> {
// @Query(value="select count(cu_dan_can_ho.id) as soluong, cu_dan.ten, cu_dan.cmt from cu_dan_can_ho inner join cu_dan on cu_dan_can_ho.id = cudancanho_id where cu_dan_can_ho.ngay_den between :ngayBatDau and :ngayKetThuc",nativeQuery=true)
// List<CuDanCanHoDTO>  staticalNguoiDen(Date ngayBatDau, Date ngayketthuc);


}
