package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SeminarDto extends BaseDto {

    private String title;

    private Date startDate;

    private Date endDate;

    private Double price;

    private Long topicEntityId;

    private Long locationEntityId;
}
