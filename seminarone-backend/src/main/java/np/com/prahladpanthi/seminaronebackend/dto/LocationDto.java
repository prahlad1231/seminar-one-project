package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationDto extends BaseDto {

    private String venueName;

    private Integer streetNumber;

    private String streetName;

    private String state;

    private String website;
}
