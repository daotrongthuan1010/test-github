package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class DichVu extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Float giatien;
    @NotNull
    private String donViTinh;
    @NotNull
    private String tendv;
    @OneToMany(mappedBy="dichVu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HoaDon> hoaDons;


}
