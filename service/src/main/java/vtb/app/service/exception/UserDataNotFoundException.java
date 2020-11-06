package vtb.app.service.exception;

public class UserDataNotFoundException extends RuntimeException{
    public UserDataNotFoundException(String message) {
        super(message);
    }
}
