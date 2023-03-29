package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.entity.HoaDon;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {

    @Query(value="select u.id, u.ho_ten, u.email, d.tendv, d.don_vi_tinh, " +
            "d.giatien,h.so_da_dung, h.tong_tien, h.created_date from quanly.hoa_don h" +
            " inner join quanly.user u on h.user_id = u.id  " +
            "inner join  quanly.dich_vu d on h.dichvu_id = d.id", nativeQuery=true)
    List<Map<String, Object>> findAllHoaDon();


    @Query(value ="select u.id, u.ho_ten, u.email, d.tendv, d.don_vi_tinh, d.giatien, h.so_da_dung, h.tong_tien, h.created_date " +
            "from quanly.hoa_don h inner join" +
            " quanly.user u on h.user_id = u.id  inner " +
            "join  quanly.dich_vu d on h.dichvu_id = d.id where u.username =:name", nativeQuery=true)
    List<Map<String, Object>> findHoaDonByUser(String name);

    @Query(value= "SELECT d.id, d.tendv, sum(h.tong_tien)" +
            " as tongTienDV f" +
            "rom  quanly.dich_vu d left join quanly.hoa_don h " +
            "on d.id = h.dichvu_id " +
            "where h.created_date " +
            "between :startDate  and :endDate  " +
            "group by d.id order by tongTienDV asc ;", nativeQuery=true)
    List<Map<String, Object>> getHoaDonByDichVu2(LocalDateTime startDate, LocalDateTime endDate);
    @Query(value= "SELECT d.id, d.tendv, sum(h.tong_tien)" +
            " as tongTienDV f" +
            "rom  quanly.dich_vu d left join quanly.hoa_don h " +
            "on d.id = h.dichvu_id " +
            "where h.created_date " +
            "between :startDate  and :endDate  " +
            "group by d.id order by tongTienDV asc ;", nativeQuery=true)
    List<Map<String, Object>> getHoaDonByDichVu(Date startDate, Date endDate);


}
