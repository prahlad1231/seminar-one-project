package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "branch")
@Builder
@AllArgsConstructor
public class BranchEntity extends BaseEntity {

    @NotNull
    @Column(name = "branch_number", unique = true)
    private Integer branchNumber;

    @NotNull
    private String address;

    private String telephone;

    public BranchEntity() {

    }
}
