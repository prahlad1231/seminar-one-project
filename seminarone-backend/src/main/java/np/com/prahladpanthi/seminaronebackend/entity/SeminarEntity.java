package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "seminar")
public class SeminarEntity extends BaseEntity {

    private String title;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private Double price;

    @OneToOne
    private TopicEntity topicEntity;

    @OneToOne
    private LocationEntity locationEntity;
}
