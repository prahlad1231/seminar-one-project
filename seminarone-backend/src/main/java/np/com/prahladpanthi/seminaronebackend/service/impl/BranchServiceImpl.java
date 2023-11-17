package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.dto.BranchDto;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.mapper.BranchMapper;
import np.com.prahladpanthi.seminaronebackend.repository.BranchRepository;
import np.com.prahladpanthi.seminaronebackend.service.IBranchService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl extends BaseServiceImpl<BranchEntity, Long> implements IBranchService {

    private BranchRepository branchRepository;

    private BranchMapper branchMapper;

    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper) {
        super(branchRepository);
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
    }

    @Override
    public BranchDto findByBranchNumber(Integer branchNumber) {
        BranchEntity branchEntity = branchRepository.findByBranchNumber(branchNumber);
        if (branchEntity == null) throw new NotFoundException("Branch with branch number " + branchNumber + " not found!");
        return branchMapper.mapToDto(branchEntity);
    }

    @Override
    public boolean existsByBranchNumber(Integer branchNumber) {
        return branchRepository.existsByBranchNumber(branchNumber);
    }
}
