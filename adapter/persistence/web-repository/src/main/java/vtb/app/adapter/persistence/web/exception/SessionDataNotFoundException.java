package vtb.app.adapter.persistence.web.exception;

public class SessionDataNotFoundException extends RuntimeException{
    public SessionDataNotFoundException(String message) {
        super(message);
    }
}
