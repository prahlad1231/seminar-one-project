package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDto extends BaseDto {

    private Integer branchNumber;

    private String address;

    private String telephone;
}
