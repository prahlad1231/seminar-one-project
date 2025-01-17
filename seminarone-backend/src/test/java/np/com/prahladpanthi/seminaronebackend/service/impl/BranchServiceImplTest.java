package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.dto.BranchDto;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import np.com.prahladpanthi.seminaronebackend.mapper.BranchMapper;
import np.com.prahladpanthi.seminaronebackend.repository.BranchRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchServiceImplTest {
    @Mock
    private BranchRepository branchRepository;

    @Mock
    private BranchMapper branchMapper;

    @InjectMocks
    private BranchServiceImpl branchService;

    @Test
    public void test_createNewBranch() {
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

    @Test
    public void test_findByBranchNumber_Success() {
        // Arrange
        Long branchId = 1L;
        Integer branchNumber = 101;
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setId(branchId);
        branchEntity.setBranchNumber(branchNumber);

        BranchDto branchDto = new BranchDto();
        branchDto.setId(branchId);
        branchDto.setBranchNumber(branchNumber);

        when(branchRepository.findByBranchNumber(branchNumber)).thenReturn(branchEntity);
        when(branchMapper.mapToDto(branchEntity)).thenReturn(branchDto);

        // Act
        BranchDto result = branchService.findByBranchNumber(branchNumber);

        // Result
        assertNotNull(result);
        assertEquals(branchNumber, result.getBranchNumber());
        verify(branchRepository).findByBranchNumber(branchNumber);
        verify(branchMapper).mapToDto(branchEntity);

    }


}