package seung.spring.bibimbap.mining;

public class SMiningException extends Exception {

	private static final long serialVersionUID = 6919242972932361468L;
	
	public SMiningException(String message) {
		super(message);
	}
	
	public SMiningException(String message, Throwable cause) {
		super(message, cause);
	}
	
}