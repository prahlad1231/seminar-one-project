package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seminar_form")
public class ApplicationFormEntity extends BaseEntity {

    private String title;

    @Column(name = "number_of_days")
    private Integer numberOfDays;

    @Column(name = "is_trade_show")
    private Boolean isTradeShow;

    @ManyToOne
    private SeminarEntity seminarEntity;

    @ManyToOne
    private UserEntity userEntity;
}
