package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    private String password;

    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    private String email;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RoleEntity> roleEntityList = new ArrayList<>();

}
