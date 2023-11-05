package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StaffDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String position;

    private String gender;

    private Date dateOfBirth;

    private String internalTelephoneNumber;

    private Long branchEntityId;
}
