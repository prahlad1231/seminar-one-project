package np.com.prahladpanthi.seminaronebackend.exception;

import np.com.prahladpanthi.seminaronebackend.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDto> handleNoteNotFoundException(NotFoundException nnfe) {
        return new ResponseEntity<>(new ResponseDto(nnfe.getMessage()), HttpStatus.NOT_FOUND);
    }
}
