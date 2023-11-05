package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviewer")
public class ReviewerEntity extends BaseEntity {

    @OneToOne
    private StaffEntity staffEntity;
}
