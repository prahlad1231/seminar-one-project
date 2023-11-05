package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewerDecisionDto extends BaseDto {

    private Date reviewDate;

    private Boolean isAccepted;

    private String comments;

    private Long applicationFormEntityId;

    private Long reviewerEntityId;
}
