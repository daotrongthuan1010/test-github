package thayduc.quanlydancu.demo.service.serviceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.entity.CuDanCanHo;
import thayduc.quanlydancu.demo.repository.QuanLyCuDanCanHoRepository;
import thayduc.quanlydancu.demo.service.QuanLyCuDanCanHoService;

import java.util.List;

@Service
@Transactional
public class QuanLyCuDanCanHoServiceIpm implements QuanLyCuDanCanHoService {
    @Autowired
    private QuanLyCuDanCanHoRepository quanLyCuDanCanHoRepository;
    @Override
    public void addCuDanCanHo(CuDanCanHo cuDanCanHo) {
        quanLyCuDanCanHoRepository.save(cuDanCanHo);
    }

    @Override
    public List<CuDanCanHo> findByAll() {
        List<CuDanCanHo> list = quanLyCuDanCanHoRepository.findAll();
        return  list;
    }
}
