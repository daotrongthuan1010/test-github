package thayduc.quanlydancu.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


@MappedSuperclass
@Data
@NoArgsConstructor
public class AbstractEntity implements Serializable {
    @CreatedBy
    @Column(name = "created_by", updatable = false, length = 50)
    protected String createdBy;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    protected LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected String lastModifiedBy ;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected LocalDateTime lastModifiedDate = LocalDateTime.now();
}
