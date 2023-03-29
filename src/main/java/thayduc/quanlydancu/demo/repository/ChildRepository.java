package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.entity.HoaDon;

/**
 * @author thuandao1010
 * @version 1.0
 * @since 2023-02-11
 */
@Repository
public interface ChildRepository extends JpaRepository<HoaDon, Long> {

}
