package tech.talci.talcibankspringrest.exceptions;

public class TalciBankException extends RuntimeException{

    public TalciBankException() {
        super();
    }

    public TalciBankException(String message) {
        super(message);
    }

    public TalciBankException(String message, Throwable cause) {
        super(message, cause);
    }

    public TalciBankException(Throwable cause) {
        super(cause);
    }

    protected TalciBankException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
