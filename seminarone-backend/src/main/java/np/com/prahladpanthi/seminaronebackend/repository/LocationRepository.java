package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
