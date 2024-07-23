package np.com.prahladpanthi.seminaronebackend.service;

import np.com.prahladpanthi.seminaronebackend.dto.BookingDto;
import np.com.prahladpanthi.seminaronebackend.entity.BookingEntity;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.service.base.IBaseService;

public interface IBookingService extends IBaseService<BookingEntity, Long> {

    BookingDto bookSeminar(BookingDto bookingDto, final UserEntity user);
}
