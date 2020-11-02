package vtb.app.adapter.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* Класс является обработчиком исключений, возникающий в контроллере
*/
@RestControllerAdvice
public class RestResponseExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass().getTypeName());
    @ExceptionHandler(value = {InvalidJWTException.class})
    protected ResponseEntity<Object> handleInvalidJWTException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(getBody(HttpStatus.INTERNAL_SERVER_ERROR, ex,
                "Can not find entity"), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private Map<String, Object> getBody(HttpStatus status, Exception ex, String message) {
        log.error(message, ex);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", message);
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("exception", ex.toString());
        Throwable cause = ex.getCause();
        if (cause != null) {
            body.put("exceptionCause", ex.getCause().toString());
        }
        return body;
    }
}
