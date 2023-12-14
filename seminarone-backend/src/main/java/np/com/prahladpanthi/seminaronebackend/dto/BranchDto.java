package np.com.prahladpanthi.seminaronebackend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto extends BaseDto {

    @NotNull
    private Integer branchNumber;

    private String address;

    private String telephone;
}
