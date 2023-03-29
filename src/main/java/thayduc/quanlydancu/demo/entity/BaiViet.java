package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data

public class BaiViet extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String noiDung;
    private String image;
    @Transient
    private MultipartFile file;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "baiViet")
    private List<Comment> comments;



}
