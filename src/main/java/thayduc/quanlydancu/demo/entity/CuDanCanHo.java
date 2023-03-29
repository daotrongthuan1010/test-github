package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;
import thayduc.quanlydancu.demo.service.CanHoService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity
@Data

public class CuDanCanHo extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayDen;
    private String trangThai;
    @OneToOne(mappedBy = "cuDanCanHo")
    private CuDan cuDan;
    @ManyToOne
    @JoinColumn(name="canho_id")
    private CanHo canHo;
}
