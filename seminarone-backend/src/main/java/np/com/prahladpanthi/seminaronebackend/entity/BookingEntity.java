package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {

    @ManyToOne
    private SeminarEntity seminarEntity;

    @ManyToOne
    private UserEntity userEntity;

    private String bookingNotes;
}
