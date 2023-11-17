package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.dto.BranchDto;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

public interface IBranchService extends IBaseService<BranchEntity, Long> {

    BranchDto findByBranchNumber(Integer branchNumber);

    boolean existsByBranchNumber(Integer branchNumber);

}
