package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import thayduc.quanlydancu.demo.entity.CuDan;
import java.util.Optional;

public interface QuanLyDanCuRepository extends JpaRepository<CuDan, Long> {
    @Query(value = "SELECT * FROM quanlychungcu.cu_dan u WHERE u.cmt like :cmt",nativeQuery=true)
    Optional<CuDan> findByCanHo(String cmt);
    @Query(value = "SELECT * FROM quanlychungcu.cu_dan u WHERE u.email like :email ",nativeQuery=true)
    Optional<CuDan> findByEmail(String email);
    @Query(value = "SELECT * FROM quanlychungcu.cu_dan u WHERE u.sodt like :sdt ",nativeQuery=true)
    Optional<CuDan> findBySdt(String sdt);


}
