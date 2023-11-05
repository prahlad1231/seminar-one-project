package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
