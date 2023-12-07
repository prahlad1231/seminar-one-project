package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.dto.custom.SeminarListCustomDto;
import np.com.prahladpanthi.seminaronebackend.entity.SeminarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeminarRepository extends JpaRepository<SeminarEntity, Long> {

    @Query("select new np.com.prahladpanthi.seminaronebackend.dto.custom.SeminarListCustomDto" +
            "(s.id, s.title, s.startDate, s.endDate, s.price, t.name, l.venueName) from SeminarEntity s " +
            "join s.topicEntity t join s.locationEntity l ")
    List<SeminarListCustomDto> findAllCustomSeminar();
}
