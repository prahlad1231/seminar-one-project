package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class LocationEntity extends BaseEntity {

    @NotNull
    @Column(name = "venue_name", unique = true)
    private String venueName;

    @Column(name = "street_number")
    private Integer streetNumber;

    @Column(name = "street_name")
    private String streetName;

    private String state;

    private String website;
}
