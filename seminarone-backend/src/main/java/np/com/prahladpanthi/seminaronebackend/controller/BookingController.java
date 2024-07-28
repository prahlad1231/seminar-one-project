package np.com.prahladpanthi.seminaronebackend.controller;

import np.com.prahladpanthi.seminaronebackend.dto.BookingDto;
import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import np.com.prahladpanthi.seminaronebackend.entity.UserEntity;
import np.com.prahladpanthi.seminaronebackend.exception.NotFoundException;
import np.com.prahladpanthi.seminaronebackend.service.IBookingService;
import np.com.prahladpanthi.seminaronebackend.service.IUserService;
import np.com.prahladpanthi.seminaronebackend.util.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(APIConstants.BOOKING)
public class BookingController extends BaseController {

    private final IUserService userService;

    private final IBookingService bookingService;

    @Autowired
    public BookingController(IUserService userService, IBookingService bookingService) {
        super(userService);
        this.userService = userService;
        this.bookingService = bookingService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(APIConstants.BOOK_SEMINAR)
    public ResponseEntity<ResponseDto> bookSeminar(Authentication auth,
                                                   @RequestBody BookingDto bookingDto) {

        final UserEntity user = getLoggedInUser(auth);

        BookingDto bookingDetails = bookingService.bookSeminar(bookingDto, user);
        return new ResponseEntity<>(new ResponseDto("Successfully booked!", bookingDetails), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(APIConstants.FIND_ALL)
    public ResponseEntity<ResponseDto> getAllBookedSeminar(Authentication auth) {
        final Long userId = getLoggedInUser(auth).getId();
        return new ResponseEntity<>(new ResponseDto("Successfully fetched!",
                bookingService.getAllBookedSeminar(userId)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(APIConstants.DELETE_BY_ID)
    public ResponseEntity<ResponseDto> deleteBookingById(@PathVariable Long id) {
        if (!bookingService.existsById(id)) throw new NotFoundException("Booking details not found!");
        bookingService.deleteById(id);
        return new ResponseEntity<>(new ResponseDto("Successfully deleted!"), HttpStatus.OK);
    }
}
