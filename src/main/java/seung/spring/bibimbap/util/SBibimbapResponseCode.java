package seung.spring.bibimbap.util;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;

import seung.java.kimchi.exception.SKimchiException;

public enum SBibimbapResponseCode {

    Success(null, HttpStatus.OK, "0000")
    , Exception(Exception.class, HttpStatus.INTERNAL_SERVER_ERROR, "E999")
    , SKimchiException(SKimchiException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E901")
    , SBibimbapException(SBibimbapException.class, HttpStatus.INTERNAL_SERVER_ERROR, "E902")
    , ConstraintViolationException(ConstraintViolationException.class, HttpStatus.BAD_REQUEST, "E401")
    , MissingServletRequestParameterException(MissingServletRequestParameterException.class, HttpStatus.BAD_REQUEST, "E402")
    ;
    
    private final Class<?> exception;
    private final HttpStatus httpStatus;
    private final String errorCode;
    
    private SBibimbapResponseCode(Class<?> exception, HttpStatus httpStatus, String errorCode) {
        this.httpStatus = httpStatus;
        this.exception = exception;
        this.errorCode = errorCode;
    }
    
    public static SBibimbapResponseCode matchError(Class<?> exception) {
        for(SBibimbapResponseCode sError : SBibimbapResponseCode.values()) {
            if(exception.equals(sError.exception())) {
                return sError;
            }
        }
        return SBibimbapResponseCode.Exception;
    }
    
    public int httpStatus() {
        return this.httpStatus.value();
    }
    
    public String errorCode() {
        return this.errorCode;
    }
    
    public Class<?> exception() {
        return this.exception;
    }
    
}
