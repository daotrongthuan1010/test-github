package thayduc.quanlydancu.demo.service;

import org.springframework.stereotype.Service;
import thayduc.quanlydancu.demo.entity.CanHo;

import java.util.Optional;

@Service
public interface CanHoService {
    Optional<CanHo> findByName(String name);
}
