package np.com.prahladpanthi.seminaronebackend.service.impl;

import np.com.prahladpanthi.seminaronebackend.dto.BookingDto;
import np.com.prahladpanthi.seminaronebackend.dto.custom.CustomBookingDto;
import np.com.prahladpanthi.seminaronebackend.entity.BookingEntity;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.exception.AlreadyExistsException;
import np.com.prahladpanthi.seminaronebackend.exception.InsufficientDataException;
import np.com.prahladpanthi.seminaronebackend.mapper.BookingMapper;
import np.com.prahladpanthi.seminaronebackend.repository.BookingRepository;
import np.com.prahladpanthi.seminaronebackend.service.IBookingService;
import np.com.prahladpanthi.seminaronebackend.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookSeminarServiceImpl extends BaseServiceImpl<BookingEntity, Long> implements IBookingService {

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;

    @Autowired
    public BookSeminarServiceImpl(BookingRepository bookingRepository,
                                  BookingMapper bookingMapper) {
        super(bookingRepository);
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public BookingDto bookSeminar(BookingDto bookingDto, final UserEntity user) {
        if (bookingDto.getSeminarEntityId() == null) {
            throw new InsufficientDataException("PLease select a seminar to book.");
        }
        if (user.getRolesEntity().getName().equals("ROLE_ADMIN")) {
            if (bookingDto.getUserEntityId() == null) {
                throw new InsufficientDataException("Please enter the user id!");
            }
            // todo: book seminar for user
        }

        if (bookingDto.getUserEntityId() == null) bookingDto.setUserEntityId(user.getId());
        if (hasBooked(bookingDto.getSeminarEntityId(), bookingDto.getUserEntityId())) {
            throw new AlreadyExistsException("You have already booked this seminar!");
        }
        BookingEntity bookingEntity = bookingRepository.save(bookingMapper.mapToEntity(bookingDto));

        return bookingMapper.mapToDto(bookingEntity);
    }

    @Override
    public boolean hasBooked(Long seminarEntityId, Long userEntityId) {
        return bookingRepository.existsBySeminarEntityIdAndUserEntityId(seminarEntityId, userEntityId);
    }

    @Override
    public List<CustomBookingDto> getAllBookedSeminar(Long userId) {
        return bookingRepository.findAllBookingByUserEntityId(userId);
    }
}
