package thayduc.quanlydancu.demo.service;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import thayduc.quanlydancu.demo.entity.CuDan;

import java.util.List;
import java.util.Optional;


public interface QuanLyCuDanService {
    void addCuDan(CuDan cuDan);
    List<CuDan> findByAll();
    void deleteById(Long id);
    Optional<CuDan> findBycmt(String cmt);
    Optional<CuDan> findById(Long id);
    Optional<CuDan> findByEmail(String email) ;
    Optional<CuDan> findBySdt(String sdt);



}
