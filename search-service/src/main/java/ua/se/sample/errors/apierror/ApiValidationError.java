package ua.se.sample.errors.apierror;

import lombok.*;


@Getter
@EqualsAndHashCode(callSuper = false)
@Builder(toBuilder = true)
public class ApiValidationError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiValidationError() {
    }

    public ApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

