package thayduc.quanlydancu.demo.repository;

import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.entity.BaiViet;
import thayduc.quanlydancu.demo.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface BaiVietRepository extends JpaRepository<BaiViet, Long> {
    @Query(value="SELECT b. id, b.noi_dung, b.image, b.last_modified_date, u.ho_ten,u.img_avatar   FROM quanly.bai_viet b inner join quanly.user u on b.user_id = u.id  order by b.last_modified_date  desc ; ",nativeQuery=true)
    List<Map<String, Object>> findUserByBaiViet();


}
