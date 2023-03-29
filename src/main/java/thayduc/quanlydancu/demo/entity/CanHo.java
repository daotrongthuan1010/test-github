package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class CanHo extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String ten;
    @NotNull
    private int tang;
    @NotNull
    private Float dientich;
    @OneToMany(mappedBy="canHo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HopDong> hopDongs;
    @ManyToOne
    @JoinColumn(name="toanha_id")
    private ToaNha toaNha;
    @OneToMany(mappedBy="canHo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HoaDon> hoaDons;

}
