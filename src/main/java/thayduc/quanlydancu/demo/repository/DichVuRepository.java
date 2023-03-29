package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.dto.DichVuDTO;
import thayduc.quanlydancu.demo.entity.DichVu;

import java.util.List;
import java.util.Map;
@Repository
public interface  DichVuRepository extends JpaRepository<DichVu, Long> {

    @Query(value="SELECT d.id, d.giatien, d.don_vi_tinh  FROM quanly.dich_vu d where d.tendv=:name", nativeQuery = true)
    List<Map<String, Object>> findByName(String name);
    @Query(value="SELECT  d.giatien, d.don_vi_tinh FROM quanly.dich_vu d where d.id=:id", nativeQuery = true)
     Map<String, Object> findByIds(Long id);
}
