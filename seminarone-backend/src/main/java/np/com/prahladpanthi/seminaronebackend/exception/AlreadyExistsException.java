package np.com.prahladpanthi.seminaronebackend.exception;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String msg) {
        super(msg);
    }

    public AlreadyExistsException(String msg, Exception ex) {
        super(msg, ex);
    }
}
