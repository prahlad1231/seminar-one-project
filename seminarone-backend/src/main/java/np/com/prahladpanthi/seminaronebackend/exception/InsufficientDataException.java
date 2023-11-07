package np.com.prahladpanthi.seminaronebackend.exception;

public class InsufficientDataException extends RuntimeException {

    public InsufficientDataException() {
        super();
    }

    public InsufficientDataException(String msg) {
        super(msg);
    }

    public InsufficientDataException(String msg, Exception ex) {
        super(msg, ex);
    }
}
