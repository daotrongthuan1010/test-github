package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class HoaDon extends  AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal tongTien;
    private Long soDaDung;
    @ManyToOne
    @JoinColumn(name="dichvu_id")
    private DichVu dichVu;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="canho_id")
    private CanHo canHo;






}
