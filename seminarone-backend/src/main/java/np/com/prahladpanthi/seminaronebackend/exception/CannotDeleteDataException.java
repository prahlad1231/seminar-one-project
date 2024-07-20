package np.com.prahladpanthi.seminaronebackend.exception;

public class CannotDeleteDataException extends RuntimeException {

    public CannotDeleteDataException(String msg) {
        super(msg);
    }

    public CannotDeleteDataException(String msg, Exception ex) {
        super(msg, ex);
    }
}
