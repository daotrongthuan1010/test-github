package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data

public class HopDong extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String quyensd;
    @NotBlank
    private String quenhevoichuho;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngaybatdau;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayketthuc;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="canho_id")
    private CanHo canHo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cudan_id")
    private CuDan cuDan;

}
