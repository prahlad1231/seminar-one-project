package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LocationDto extends BaseDto {

    private String venueName;

    private Integer streetNumber;

    private String streetName;

    private String state;

    private String website;
}
