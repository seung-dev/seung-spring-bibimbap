package seung.spring.bibimbap.util;

public class SBibimbapException extends Exception {

    private static final long serialVersionUID = 5260913034902460141L;
    
    public SBibimbapException(Throwable e) {
        super(e);
    }
    
    public SBibimbapException(String message) {
        super(message);
    }
    
    public SBibimbapException(String message, Throwable e) {
        super(message, e);
    }
    
}
