package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String username;

    private Boolean active;

    private String postalCode;

    private String email;

    private String creditCardNumber;

    private Date expiryDate;

    private Long roleEntityId;
}
