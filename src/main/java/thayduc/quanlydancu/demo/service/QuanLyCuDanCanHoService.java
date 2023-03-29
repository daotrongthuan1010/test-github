package thayduc.quanlydancu.demo.service;

import thayduc.quanlydancu.demo.entity.CuDanCanHo;

import java.util.List;

public interface QuanLyCuDanCanHoService {
    void addCuDanCanHo(CuDanCanHo cuDanCanHo);
    List<CuDanCanHo> findByAll();
}
