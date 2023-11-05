package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
}
