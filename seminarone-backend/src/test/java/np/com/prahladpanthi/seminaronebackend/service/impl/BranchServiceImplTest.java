package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.dto.BranchDto;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.repository.BranchRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceImplTest {
    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchServiceImpl branchService;

    @Test
    public void BranchService_CreateBranch_ReturnBranchDto() {
        BranchEntity branch = BranchEntity.builder()
                .branchNumber(1)
                .address("21 Beissel Street")
                .telephone("0426356355")
                .build();

        when(branchRepository.save(Mockito.any(BranchEntity.class)))
                .thenReturn(branch);

        BranchEntity savedBranch = branchService.save(branch);
        Assertions.assertThat(savedBranch).isNotNull();

    }


}