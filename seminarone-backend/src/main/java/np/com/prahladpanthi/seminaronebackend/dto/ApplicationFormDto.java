package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationFormDto extends BaseDto {

    private String title;

    private Integer numberOfDays;

    private Boolean isTradeShow;

    private Long seminarEntityId;

    private Long userEntityId;
}
