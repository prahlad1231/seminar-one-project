package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.entity.RolePermissionEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

import java.util.List;

public interface IRolePermissionService extends IBaseService<RolePermissionEntity, Long> {

    List<RolePermissionEntity> findAllByRoleId(Long roleId);
}
