package thayduc.quanlydancu.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.entity.HoaDon;

@Repository
public interface ThanhToanRepository extends JpaRepository<HoaDon, Long> {

  @Override
  Optional<HoaDon> findById(Long aLong);
}
