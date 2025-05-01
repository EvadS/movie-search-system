package ua.se.sample.errors.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RuntimeException {
    private String resourceName;

    /**
     * Instantiates a new instance of {@link AlreadyExistsException}.
     *
     * @param resourceName  searched resource
     */
    public AlreadyExistsException(String resourceName) {
        super(String.format("'%s' already exists", resourceName));
        this.resourceName = resourceName;
    }
}