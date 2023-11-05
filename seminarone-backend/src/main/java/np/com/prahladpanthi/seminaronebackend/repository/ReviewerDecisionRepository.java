package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.entity.ReviewerDecisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerDecisionRepository extends JpaRepository<ReviewerDecisionEntity, Long> {
}
