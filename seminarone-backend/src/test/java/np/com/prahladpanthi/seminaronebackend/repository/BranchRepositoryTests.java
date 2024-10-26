package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BranchRepositoryTests {

    @Autowired
    private BranchRepository branchRepository;

    @Test
    public void BranchRepository_Save_ReturnsMoreThanZeroBranch() {
        BranchEntity branchEntity = BranchEntity.builder()
                .branchNumber(1)
                .address("21 Beissel Street, Belconnen")
                .telephone("0465789678")
                .build();

        BranchEntity savedBranch = branchRepository.save(branchEntity);

        Assertions.assertThat(savedBranch).isNotNull();
        Assertions.assertThat(savedBranch.getId()).isGreaterThan(0);
    }

    @Test
    public void BranchRepository_SaveAll_ReturnAllSavedBranches() {
        BranchEntity branch1 = BranchEntity.builder()
                .branchNumber(1)
                .address("Belconnen")
                .telephone("09886737")
                .build();
        BranchEntity branch2 = BranchEntity.builder()
                .branchNumber(2)
                .address("Bruce")
                .telephone("097847444")
                .build();

        branchRepository.save(branch1);
        branchRepository.save(branch2);

        List<BranchEntity> branchEntityList = branchRepository.findAll();

        Assertions.assertThat(branchEntityList).isNotNull();
        Assertions.assertThat(branchEntityList.size()).isEqualTo(2);
    }

    @Test
    public void BranchRepository_FindById_ReturnBranchById() {
        BranchEntity branch = BranchEntity.builder()
                .branchNumber(1)
                .address("Belconnen")
                .telephone("0984644545")
                .build();

        branchRepository.save(branch);

        BranchEntity savedBranch = branchRepository.save(branch);

        BranchEntity branchEntity = branchRepository.findById(savedBranch.getId()).get();

        Assertions.assertThat(branchEntity).isNotNull();
    }

    @Test
    public void BranchRepository_FindByBranchNumber_ReturnBranchWithMatchingBranchNumber() {
        BranchEntity branch = BranchEntity.builder()
                .branchNumber(1)
                .address("Bruce")
                .telephone("0984678544")
                .build();

        branchRepository.save(branch);

        BranchEntity branchEntity = branchRepository.findByBranchNumber(branch.getBranchNumber());

        Assertions.assertThat(branchEntity).isNotNull();
        Assertions.assertThat(branchEntity.getBranchNumber()).isEqualTo(branch.getBranchNumber());
    }

    @Test
    public void BranchRepository_Update_ReturnUpdatedBranch() {
        BranchEntity branch = BranchEntity.builder()
                .branchNumber(1)
                .address("Bruce")
                .telephone("0984678544")
                .build();

        branchRepository.save(branch);

        String newAddress = "Belconnen";
        String newTelephone = "042525525";

        BranchEntity savedBranch = branchRepository.findByBranchNumber(branch.getBranchNumber());
        savedBranch.setAddress(newAddress);
        savedBranch.setTelephone(newTelephone);

        BranchEntity updatedBranch = branchRepository.save(savedBranch);

        Assertions.assertThat(updatedBranch).isNotNull();
        Assertions.assertThat(updatedBranch.getAddress()).isNotNull();
        Assertions.assertThat(updatedBranch.getTelephone()).isNotNull();
        Assertions.assertThat(updatedBranch.getTelephone()).isEqualTo(newTelephone);
        Assertions.assertThat(updatedBranch.getAddress()).isEqualTo(newAddress);
    }

    @Test
    public void BranchRepository_DeleteById_DeleteBranchById() {
        BranchEntity branch = BranchEntity.builder()
                .branchNumber(1)
                .address("Bruce")
                .telephone("0984678544")
                .build();

        BranchEntity savedBranch = branchRepository.save(branch);

        branchRepository.deleteById(savedBranch.getId());
        Optional<BranchEntity> branchEntity = branchRepository.findById(savedBranch.getId());

        Assertions.assertThat(branchEntity).isEmpty();
    }
}
