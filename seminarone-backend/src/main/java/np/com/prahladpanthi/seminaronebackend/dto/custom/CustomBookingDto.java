package np.com.prahladpanthi.seminaronebackend.dto.custom;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class CustomBookingDto {

    private Long id;

    private String seminarName;

    private Date startDate;

    private Date endDate;

    private Double price;

    private String topicName;

    private String venueName;

    private String bookingNotes;

    public CustomBookingDto(Long bookingId, String seminarName, Date startDate, Date endDate,
                            Double price, String topicName, String venueName, String bookingNotes) {
        this.id = bookingId;
        this.seminarName = seminarName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.topicName = topicName;
        this.venueName = venueName;
        this.bookingNotes = bookingNotes;
    }
}
