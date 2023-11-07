package np.com.prahladpanthi.seminaronebackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    private String message;

    private Object object;

    public ResponseDto() {
    }

    public ResponseDto(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public ResponseDto(String message) {
        this.message = message;
    }
}
