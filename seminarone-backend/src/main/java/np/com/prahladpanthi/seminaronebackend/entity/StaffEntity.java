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
@Table(name = "staff")
public class StaffEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String position;

    private String gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "internal_telephone_number")
    private String internalTelephoneNumber;

    @OneToOne
    private BranchEntity branchEntity;
}
