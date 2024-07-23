package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookingDto extends BaseDto {

    private Long seminarEntityId;

    private Long userEntityId;

    private String bookingNotes;
}
