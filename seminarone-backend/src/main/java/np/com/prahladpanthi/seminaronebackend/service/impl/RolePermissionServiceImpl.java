package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.entity.RolePermissionEntity;
import np.com.prahladpanthi.seminaronebackend.repository.RolesPermissionRepository;
import np.com.prahladpanthi.seminaronebackend.service.IRolePermissionService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionEntity, Long> implements IRolePermissionService {

    private RolesPermissionRepository rolesPermissionRepository;

    @Autowired
    public RolePermissionServiceImpl(RolesPermissionRepository rolesPermissionRepository) {
        super(rolesPermissionRepository);
        this.rolesPermissionRepository = rolesPermissionRepository;
    }


    @Override
    public List<RolePermissionEntity> findAllByRoleId(Long roleId) {
        return rolesPermissionRepository.findAllByRolesEntityId(roleId);
    }
}
