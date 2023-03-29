package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@Data
public class ChungCu extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String chudautu;

    private String ten;

    private String tenquanly;

    private Float dientich;
    @OneToMany(mappedBy="chungCu", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ToaNha> toaNhas;

    public ChungCu(Long id, String chudautu, String ten, String tenquanly, Float dientich, Set<ToaNha> toaNhas) {
        this.id = id;
        this.chudautu = chudautu;
        this.ten = ten;
        this.tenquanly = tenquanly;
        this.dientich = dientich;
        this.toaNhas = toaNhas;

    }

}
