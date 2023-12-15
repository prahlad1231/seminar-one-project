package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.dto.BranchDto;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.BranchMapper;
import np.com.prahladpanthi.seminaronebackend.repository.BranchRepository;
import np.com.prahladpanthi.seminaronebackend.service.impl.BranchServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BranchServiceImplTests {

    @Mock
    private BranchRepository branchRepository;

    @Mock
    private BranchMapper branchMapper;

    @InjectMocks
    private BranchServiceImpl branchService;


//    @Test
//    public void findByBranchNumber_returnBranchDto() {
//        BranchEntity branch = BranchEntity.builder()
//                .branchNumber(1)
//                .telephone("909392834")
//                .address("Belconnen")
//                .build();
//
//        when(branchRepository.findByBranchNumber(1)).thenReturn(branch);
//
//        BranchDto requiredBranch = branchService.findByBranchNumber(1);
//
//        Assertions.assertThat(requiredBranch).isNotNull();
//
//    }


}
