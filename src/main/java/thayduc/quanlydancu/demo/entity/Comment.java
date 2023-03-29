package thayduc.quanlydancu.demo.entity;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table

public class Comment extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baiviet_id", nullable = false)
    private BaiViet baiViet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(Long id, String content, BaiViet baiViet, User user) {
        this.id = id;
        this.content = content;
        this.baiViet = baiViet;
        this.user = user;
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BaiViet getBaiViet() {
        return baiViet;
    }

    public void setBaiViet(BaiViet baiViet) {
        this.baiViet = baiViet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
