package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data

public class ToaNha extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String ten;
    @OneToMany(mappedBy="toaNha", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CanHo> canHos;
    @ManyToOne
    @JoinColumn(name="chungcu_id")
    private ChungCu chungCu;


}
