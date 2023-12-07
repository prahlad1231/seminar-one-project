package np.com.prahladpanthi.seminaronebackend.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SeminarListCustomDto {

    private Long id;

    private String title;

    private Date startDate;

    private Date endDate;

    private Double price;

    private String topicName;

    private String venueName;

    public SeminarListCustomDto(Long id, String title, Date startDate, Date endDate, Double price, String topicName, String venueName) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.topicName = topicName;
        this.venueName = venueName;
    }
}
