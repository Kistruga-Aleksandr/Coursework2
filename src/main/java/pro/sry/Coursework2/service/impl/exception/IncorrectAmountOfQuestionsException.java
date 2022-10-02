package pro.sry.Coursework2.service.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectAmountOfQuestionsException extends RuntimeException {
    public IncorrectAmountOfQuestionsException() {
    }

    public IncorrectAmountOfQuestionsException(String message) {
        super(message);
    }

    public IncorrectAmountOfQuestionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectAmountOfQuestionsException(Throwable cause) {
        super(cause);
    }

    public IncorrectAmountOfQuestionsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
