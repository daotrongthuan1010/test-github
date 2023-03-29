package thayduc.quanlydancu.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import thayduc.quanlydancu.demo.entity.Comment;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value="select c.created_date, c.content, u.username, u.img_avatar from quanly.comment c inner join quanly.user u on c.user_id = u.id where c.id = :id", nativeQuery=true)
   Map<String, Object> findByIdComment(Long id);
    @Query(value="select c.last_modified_date, c.content, u.ho_ten, u.img_avatar from quanly.comment c inner join quanly.user u on c.user_id = u.id where c.baiviet_id = :idBaiViet", nativeQuery=true)
   List<Map<String, Object>> findListComment(Long idBaiViet);

}
