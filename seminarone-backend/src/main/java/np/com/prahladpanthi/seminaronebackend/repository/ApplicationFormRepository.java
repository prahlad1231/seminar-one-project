package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.entity.ApplicationFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationFormEntity, Long> {
}
