package pro.sry.Coursework2.service.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAlreadyExistsException extends RuntimeException {

    public QuestionAlreadyExistsException() {
    }

    public QuestionAlreadyExistsException(String message) {
        super(message);
    }

    public QuestionAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public QuestionAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
