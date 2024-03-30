package np.com.prahladpanthi.seminaronebackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "role_permission")
public class RolePermissionEntity extends BaseEntity {

    @ManyToOne
    private RolesEntity rolesEntity;

    @ManyToOne
    private PermissionEntity permissionEntity;
}
