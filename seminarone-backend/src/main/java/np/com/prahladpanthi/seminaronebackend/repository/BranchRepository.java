package np.com.prahladpanthi.seminaronebackend.repository;

import jakarta.annotation.Nullable;
import np.com.prahladpanthi.seminaronebackend.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<BranchEntity, Long> {

    @Nullable
    BranchEntity findByBranchNumber(Integer branchNumber);

    boolean existsByBranchNumber(Integer branchNumber);
}
