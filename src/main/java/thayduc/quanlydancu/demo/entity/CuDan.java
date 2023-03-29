package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table
public class CuDan extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Tên không được để trống")
    private String ten;
    @Past(message = "Ngày sinh trước ngày hiện tại")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaysinh;
    @NotNull
    @Column(unique = true)
    @Length(min = 9, message = "Số điện thoại phải từ 9 trở nên")
    private String soDT;
    @NotNull
    @Column(unique = true)
    @Length(min = 6,message = "Số chứng minh từ 6 số trở nên")
    private String cmt;
    @NotNull
    @Column(unique = true)
    @Email(message = "Không đúng định dạng email")
    private String email;
    @NotBlank
    private String trangthai;
    @Transient
    private MultipartFile file;

    @OneToMany(mappedBy="cuDan")
    private List<HopDong> cuDanHopDong;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cudancanho_id", referencedColumnName = "id")
    private CuDanCanHo cuDanCanHo;

}
