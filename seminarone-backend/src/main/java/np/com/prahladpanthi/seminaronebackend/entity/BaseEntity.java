package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "created_date", updatable = false)
    private final Date createdDate = new Date();

    private String status = "A"; // todo: fix this, it is not working

}
