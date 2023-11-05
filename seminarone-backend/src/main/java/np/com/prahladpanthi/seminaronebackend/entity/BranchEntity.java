package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "branch")
public class BranchEntity extends BaseEntity {

    @Column(name = "branch_number")
    private Integer branchNumber;

    private String address;

    private String telephone;
}
