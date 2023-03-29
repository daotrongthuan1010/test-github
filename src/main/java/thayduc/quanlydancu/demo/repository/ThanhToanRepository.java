<<<<<<< HEAD
package thayduc.quanlydancu.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.entity.HoaDon;

@Repository
public interface ThanhToanRepository extends JpaRepository<HoaDon, Long> {

  @Override
  Optional<HoaDon> findById(Long aLong);
=======
package thayduc.quanlydancu.demo.repository;public interface ThanhToanRepository {
>>>>>>> ce3622bd8e58cfebbf399660ee2299cc03bd2d0b
}
