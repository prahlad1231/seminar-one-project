package np.com.prahladpanthi.seminaronebackend.exception;

import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDto> handleNoteNotFoundException(NotFoundException nnfe) {
        return new ResponseEntity<>(new ResponseDto(nnfe.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InsufficientDataException.class)
    public ResponseEntity<ResponseDto> handleInsufficientDataException(InsufficientDataException ide) {
        return new ResponseEntity<>(new ResponseDto(ide.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<ResponseDto> handleAlreadyExistsException(AlreadyExistsException ex) {
        return new ResponseEntity<>(new ResponseDto(ex.getMessage()), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(value = IncorrectPasswordException.class)
    public ResponseEntity<ResponseDto> handleIncorrectPasswordException(IncorrectPasswordException ipe) {
        return new ResponseEntity<>(new ResponseDto(ipe.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CannotDeleteDataException.class)
    public ResponseEntity<ResponseDto> handleCannotDeleteDataException(CannotDeleteDataException cdde) {
        return new ResponseEntity<>(new ResponseDto(cdde.getMessage()), HttpStatus.CONFLICT);
    }
}
