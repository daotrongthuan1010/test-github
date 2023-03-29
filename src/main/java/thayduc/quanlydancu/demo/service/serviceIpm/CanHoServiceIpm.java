package thayduc.quanlydancu.demo.service.serviceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.entity.CanHo;
import thayduc.quanlydancu.demo.repository.CanHoRepository;
import thayduc.quanlydancu.demo.service.CanHoService;

import java.util.Optional;
@Service
@Transactional
public class CanHoServiceIpm implements CanHoService {
    @Autowired
    private CanHoRepository canHoRepository;
    @Override
    public Optional<CanHo> findByName(String name) {
        Optional<CanHo> optionalCanHo = canHoRepository.findByName(name);
        return optionalCanHo;
    }
}
