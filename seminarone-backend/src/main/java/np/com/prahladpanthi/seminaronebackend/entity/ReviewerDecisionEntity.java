package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "reviewer_decision")
public class ReviewerDecisionEntity extends BaseEntity {

    @Column(name = "review_date")
    private Date reviewDate;

    @Column(name = "is_accepted")
    private Boolean isAccepted;

    private String comments;

    @OneToOne
    private ApplicationFormEntity applicationFormEntity;

    @ManyToOne
    private ReviewerEntity reviewerEntity;
}
