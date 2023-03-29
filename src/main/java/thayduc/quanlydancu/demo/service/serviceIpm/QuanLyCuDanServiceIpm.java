package thayduc.quanlydancu.demo.service.serviceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.entity.CuDan;
import thayduc.quanlydancu.demo.repository.QuanLyDanCuRepository;
import thayduc.quanlydancu.demo.service.QuanLyCuDanService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class QuanLyCuDanServiceIpm implements QuanLyCuDanService {
    @Autowired
    private QuanLyDanCuRepository quanLyDanCuRepository;


    @Override
    public void addCuDan(CuDan cuDan) {
        quanLyDanCuRepository.save(cuDan);
    }

    @Override
    public List<CuDan> findByAll() {
       List<CuDan> list= quanLyDanCuRepository.findAll();
       return list;
    }

    @Override
    public void deleteById(Long id) {
        quanLyDanCuRepository.deleteById(id);
    }

    @Override
    public Optional<CuDan> findBycmt(String cmt) {
        Optional<CuDan> optionalCuDan = quanLyDanCuRepository.findByCanHo(cmt);
        return optionalCuDan;
    }

    @Override
    public Optional<CuDan> findById(Long id) {
        Optional<CuDan> optionalCuDan = quanLyDanCuRepository.findById(id);
        return optionalCuDan;
    }

    @Override
    public  Optional<CuDan> findByEmail(String email) {
        Optional<CuDan> cuDan = quanLyDanCuRepository.findByEmail(email);

        return cuDan;
    }

    @Override
    public  Optional<CuDan> findBySdt(String sdt) {
        return quanLyDanCuRepository.findBySdt(sdt);
    }
}
