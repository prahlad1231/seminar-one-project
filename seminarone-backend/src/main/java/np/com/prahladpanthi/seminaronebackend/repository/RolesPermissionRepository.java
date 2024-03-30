package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.entity.RolePermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesPermissionRepository extends JpaRepository<RolePermissionEntity, Long> {

    List<RolePermissionEntity> findAllByRolesEntityId(Long roleId);
}
