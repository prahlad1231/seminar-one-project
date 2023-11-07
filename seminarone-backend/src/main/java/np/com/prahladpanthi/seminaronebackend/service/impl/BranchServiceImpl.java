package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.repository.BranchRepository;
import np.com.prahladpanthi.seminaronebackend.service.IBranchService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl extends BaseServiceImpl<BranchEntity, Long> implements IBranchService {

    private BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository) {
        super(branchRepository);
        this.branchRepository = branchRepository;
    }
}
