package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.entity.StaffEntity;
import np.com.prahladpanthi.seminaronebackend.repository.StaffRepository;
import np.com.prahladpanthi.seminaronebackend.service.IStaffService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends BaseServiceImpl<StaffEntity, Long> implements IStaffService {

    private StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        super(staffRepository);
        this.staffRepository = staffRepository;
    }
}
