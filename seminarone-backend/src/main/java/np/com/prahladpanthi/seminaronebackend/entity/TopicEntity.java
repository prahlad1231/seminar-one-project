package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "topic")
public class TopicEntity extends BaseEntity{

    private String name;
}
