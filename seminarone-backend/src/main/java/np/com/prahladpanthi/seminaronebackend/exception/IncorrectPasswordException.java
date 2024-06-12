package np.com.prahladpanthi.seminaronebackend.exception;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String msg) { super(msg); }

    public IncorrectPasswordException(String msg, Exception ex) {
        super(msg,ex);
    }
}
