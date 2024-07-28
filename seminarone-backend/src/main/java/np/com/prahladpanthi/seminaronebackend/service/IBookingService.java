package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.dto.BookingDto;
import np.com.prahladpanthi.seminaronebackend.dto.custom.CustomBookingDto;
import np.com.prahladpanthi.seminaronebackend.entity.BookingEntity;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

import java.util.List;

public interface IBookingService extends IBaseService<BookingEntity, Long> {

    BookingDto bookSeminar(BookingDto bookingDto, final UserEntity user);

    boolean hasBooked(Long seminarEntityId, Long userEntityId);

    List<CustomBookingDto> getAllBookedSeminar(Long userId);
}
