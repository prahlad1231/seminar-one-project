package np.com.prahladpanthi.seminaronebackend.repository;

import np.com.prahladpanthi.seminaronebackend.dto.custom.CustomBookingDto;
import np.com.prahladpanthi.seminaronebackend.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    boolean existsBySeminarEntityIdAndUserEntityId(Long seminarEntityId, Long userEntityId);

    List<BookingEntity> findAllByUserEntityId(Long userId);

    @Query("select new np.com.prahladpanthi.seminaronebackend.dto.custom.CustomBookingDto(" +
            "b.id, s.title, s.startDate, s.endDate, s.price, t.name, l.venueName, b.bookingNotes) " +
            "from BookingEntity b join " +
            "b.seminarEntity s join " +
            "s.topicEntity t join " +
            "s.locationEntity l " +
            "where b.userEntity.id = ?1")
    List<CustomBookingDto> findAllBookingByUserEntityId(Long userId);

}
