package ua.se.sample.errors;

import jakarta.validation.ConstraintDefinitionException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.errors.models.ApiValidationError;
import ua.se.sample.errors.models.ErrorDetail;


import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@RestControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String TRACE = "trace";

    @Value("${books.trace:false}")
    private final boolean printStackTrace = false;


    /***************************************************************
     * VALIDATION ERRORS block
     ***************************************************************/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ApiValidationError> validationErrorList = fieldErrors.stream().map(
                i ->
                        new ApiValidationError(i.getObjectName(),
                                i.getField(),
                                i.getRejectedValue(),
                                i.getDefaultMessage())

        ).collect(Collectors.toList());

        return buildErrorResponse(ex, "Field type mismatch", "Constraint validation",
                HttpStatus.BAD_REQUEST,
                validationErrorList,
                request);
    }

    @ExceptionHandler(ConstraintDefinitionException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintDefinitionException(ConstraintDefinitionException e, WebRequest request) {
        List<ApiValidationError> validationErrorList = Collections.EMPTY_LIST;
        return buildErrorResponse(e, "Field type mismatch", "Constraint validation",
                HttpStatus.BAD_REQUEST,
                validationErrorList,
                request);
    }


    /***************************************************************
     *      block NotFound
     ***************************************************************/
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {

        log.error("Resource {} ,by: {} value {} cannot be found", e.getResourceName(),
                e.getFieldName(), e.getFieldValue());

        return buildErrorResponse(e, "Resource not found", e.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    /***************************************************************
     *      Http Media Type Not Acceptable
     ***************************************************************/
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.error("incorrect media type: {} ", ex.getMessage());

        return buildErrorResponse(ex, "not valid arguments",
                "Input Request Message cannot be processed", HttpStatus.NOT_ACCEPTABLE, request);
    }

    /***************************************************************
     * block 500
     ***************************************************************/
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final ResponseEntity<Object> handleIllegalArgument(Exception ex, WebRequest request) {
        log.error("An unexpected error occurred", ex);

        String title = "This should be application specific";
        String detail = "An unexpected error has occurred";

        return buildErrorResponse(ex, title, detail, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {

        return buildErrorResponse(e, "not valid due to validation error:", "detail",
                HttpStatus.BAD_REQUEST, request);
    }

    /***************************************************************
     * buildErrorResponse Block
     ***************************************************************/
    private ResponseEntity<Object> buildErrorResponse(Exception exception, String title, String detail,
                                                      HttpStatus httpStatus, WebRequest request) {
        return buildErrorResponse(exception, title, detail, httpStatus, null, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception, String title,
                                                      String detail, HttpStatus httpStatus,
                                                      List<ApiValidationError> errors,
                                                      WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();

        errorDetail.setMessage(title);
        errorDetail.setDetail(detail);
        errorDetail.setStatus(httpStatus.value());
        errorDetail.setErrors(errors);

        if (printStackTrace && request != null && isTraceOn(request)) {
            errorDetail.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorDetail);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }
}