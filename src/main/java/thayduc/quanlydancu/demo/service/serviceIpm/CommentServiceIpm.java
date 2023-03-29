package thayduc.quanlydancu.demo.service.serviceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thayduc.quanlydancu.demo.dto.BaiVietDTO;
import thayduc.quanlydancu.demo.dto.CommentDTO;
import thayduc.quanlydancu.demo.entity.BaiViet;
import thayduc.quanlydancu.demo.entity.Comment;
import thayduc.quanlydancu.demo.entity.User;
import thayduc.quanlydancu.demo.repository.CommentRepository;
import thayduc.quanlydancu.demo.repository.UserRepository;
import thayduc.quanlydancu.demo.service.BaiVietService;
import thayduc.quanlydancu.demo.service.CommentService;
import thayduc.quanlydancu.demo.utility.ChuanHoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceIpm implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BaiVietService baiVietService;


    @Override
    @Transactional
    public Comment addComment(Long idBaiViet, String content, User user) {
        Comment comment = new Comment();
        ChuanHoa chuanHoa = new ChuanHoa();
        String noiDung = chuanHoa.chuanHoaChuCaiDau(content);
        comment.setContent(noiDung);
        comment.setUser(user);
        Optional<BaiViet> baiViet = baiVietService.findById(idBaiViet);
        comment.setBaiViet(baiViet.get());
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }



    @Override
    public List<CommentDTO> findAllComment(Long idBaiViet) {
        List<CommentDTO> list = new ArrayList<>();
        List<Map<String, Object>> obj = commentRepository.findListComment(idBaiViet);
        obj.forEach(x ->
        {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setContent(String.valueOf(x.get("content")));
            commentDTO.setCreateDate(String.valueOf(x.get("last_modified_date")));
            commentDTO.setImages(String.valueOf(x.get("img_avatar")));
            commentDTO.setHoTen(String.valueOf(x.get("ho_ten")));

            list.add(commentDTO);

        });

        return list;


    }
}
