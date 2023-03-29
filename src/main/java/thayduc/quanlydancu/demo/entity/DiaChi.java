package thayduc.quanlydancu.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class DiaChi extends  AbstractEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String phuong;
	@NotNull
	private String quan;
	@NotNull
	private String thanhpho;
	private String mota;

	

	
	
}
