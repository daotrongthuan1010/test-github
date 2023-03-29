package thayduc.quanlydancu.demo.service;

import org.springframework.stereotype.Service;
import thayduc.quanlydancu.demo.dto.CommentDTO;
import thayduc.quanlydancu.demo.entity.Comment;
import thayduc.quanlydancu.demo.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {
    Comment addComment(Long idBaiViet, String content, User user);
    List<Comment> findAll();
     List<CommentDTO> findAllComment(Long idBaiViet);
}
