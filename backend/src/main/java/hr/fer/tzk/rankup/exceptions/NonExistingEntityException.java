package hr.fer.tzk.rankup.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NonExistingEntityException extends RuntimeException {
    private String message;

    public NonExistingEntityException() {
    }

    public NonExistingEntityException(String message) {
        super(message);
        this.message = message;
    }
}
