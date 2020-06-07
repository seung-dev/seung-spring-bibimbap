package seung.spring.bibimbap.mining.util;

public class SMiningException extends Exception {

    private static final long serialVersionUID = 6919242972932361468L;
    
    public SMiningException(Throwable e) {
        super(e);
    }
    
    public SMiningException(String message) {
        super(message);
    }
    
    public SMiningException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
