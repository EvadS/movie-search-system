package ua.se.sample.errors.apierror;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;


@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail {

    @Schema(name = "Error message", example = "Resource not found")
    private String message;

    @Schema(name = "Error Detail Description", example = "Requested resource cannot be found")
    private String detail;

    @Schema(name = "HTTP Status Code", example = "404")
    private Integer status;

    @Schema(name = "List of additional errors")
    private List<ApiValidationError> errors;

    private String stackTrace;

    public ErrorDetail() {
    }


    /**
     * @param message error title
     * @param detail  error detail
     * @param status  http status
     * @param errors  sub errors list
     */
    public ErrorDetail(String message, String detail, Integer status, List<ApiValidationError> errors) {
        this(message, detail, status, errors, null);
    }

    public ErrorDetail(String message, String detail, Integer status, List<ApiValidationError> errors, String stackTrace) {
        this.message = message;
        this.detail = detail;
        this.status = status;
        this.errors = errors;
        this.stackTrace = stackTrace;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setErrors(List<ApiValidationError> errors) {
        this.errors = errors;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
