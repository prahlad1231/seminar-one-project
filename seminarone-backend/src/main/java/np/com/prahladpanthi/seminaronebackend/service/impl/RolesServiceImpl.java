package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.entity.RolesEntity;
import np.com.prahladpanthi.seminaronebackend.repository.RolesRepository;
import np.com.prahladpanthi.seminaronebackend.service.IRolesService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl extends BaseServiceImpl<RolesEntity, Long> implements IRolesService {

    private RolesRepository rolesRepository;

    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository) {
        super(rolesRepository);
        this.rolesRepository = rolesRepository;
    }

}
