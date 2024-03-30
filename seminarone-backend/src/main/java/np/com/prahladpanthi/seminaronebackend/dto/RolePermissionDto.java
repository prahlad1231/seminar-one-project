package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermissionDto extends BaseDto {

    private Long rolesEntityId;

    private Long permissionEntityId;
}
