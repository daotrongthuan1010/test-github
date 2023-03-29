package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.entity.CanHo;

import java.util.Optional;
@Repository
public interface CanHoRepository extends JpaRepository<CanHo, Long> {
    @Query(value = "SELECT * FROM quanlychungcu.can_ho u WHERE u.ten like :name",nativeQuery=true)
    Optional<CanHo> findByName(String name);
    



}
