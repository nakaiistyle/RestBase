package jp.co.allsmart.restbase.core.exception;

import java.text.MessageFormat;

import jp.co.allsmart.restbase.core.error.HttpErrors;

public class ApplicationException extends RuntimeException {

    private Throwable cause;
    private HttpErrors error;
    private Object[] args;

    public ApplicationException(HttpErrors error, Throwable cause, String... args){
        super(cause);
        this.error = error;
        this.cause = cause;
        this.args = args;
    }

    public ApplicationException(HttpErrors error, Throwable cause){
        super(cause);
        this.error = error;
        this.cause = cause;
    }

    public ApplicationException(HttpErrors error, String... args){
        super();
        this.error = error;
        this.args = args;
    }

    public ApplicationException(HttpErrors error){
        this.error = error;
    }

    public String getMessage(){
        if(args != null){
            return "[" + error.name() + "]" +  MessageFormat.format(error.getMessage(), args);
        }
        return "[" + error.name() + "]" + error.getMessage();
    }

    /**
     * @return cause
     */
    public Throwable getCause() {
        return cause;
    }

    /**
     * @return error
     */
    public HttpErrors getError() {
        return error;
    }

    /**
     * @return args
     */
    public Object[] getArgs() {
        return args;
    }


}
