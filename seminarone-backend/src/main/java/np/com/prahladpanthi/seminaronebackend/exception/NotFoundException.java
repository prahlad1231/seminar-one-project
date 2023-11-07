package np.com.prahladpanthi.seminaronebackend.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }
    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Exception ex) {
        super(msg, ex);
    }
}
