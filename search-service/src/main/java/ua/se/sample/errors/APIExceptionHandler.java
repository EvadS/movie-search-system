package ua.se.sample.errors;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.se.sample.errors.apierror.ApiValidationError;
import ua.se.sample.errors.apierror.ErrorDetail;
import ua.se.sample.errors.exceptions.AlreadyExistsException;
import ua.se.sample.errors.exceptions.DataBaseConstraintException;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String TRACE = "trace";

    @Value("${books.trace:false}")
    private final boolean printStackTrace = false;


    /***************************************************************
     ***  VALIDATION ERRORS block
     ***************************************************************/
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ApiValidationError> validationErrorList = fieldErrors.stream().map(
                i ->
                        ApiValidationError.builder()
                                .object(i.getObjectName())
                                .field(i.getField())
                                .rejectedValue(i.getRejectedValue())
                                .message(i.getDefaultMessage())
                                .build()).collect(Collectors.toList());

        return buildErrorResponse(ex, "Field type mismatch", "Constraint validation",
                HttpStatus.BAD_REQUEST,
                validationErrorList,
                request);
    }


    @ExceptionHandler(DataBaseConstraintException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleDataBaseConstraintException(DataBaseConstraintException e, WebRequest request) {

        log.error("ConstraintViolationException. Reason: {}", e.getMessage()) ;
        return buildErrorResponse(e, "Constraint exception", e.getMessage(),
                HttpStatus.CONFLICT,  request);
    }

    /****************************************************************
     *      Block 415  HttpMediaTypeNotSupportedException
     ****************************************************************/
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                                                    HttpHeaders headers, HttpStatusCode status, WebRequest request){
        log.error("HttpMediaTypeNotSupported {}", ex.getMessage()) ;
        return buildErrorResponse(ex, "Invalid request",
                "Input Request Message cannot be processed",
                HttpStatus.UNSUPPORTED_MEDIA_TYPE,request);
    }

    @Override
    protected ResponseEntity<Object>  handleMissingPathVariable(
            MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){

         return buildErrorResponse(ex, "incorrect request",
                "Input Request Message cannot be processed", HttpStatus.BAD_REQUEST, request);

    }
    @Override
    protected ResponseEntity<Object>  handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        return buildErrorResponse(ex, "incorrect request",
                ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }


    /***************************************************************
     *      block NotFound
     ***************************************************************/
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        return buildErrorResponse(e, "Resource not found", e.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<?> handleAlreadyExistsException(AlreadyExistsException e, WebRequest request) {
        return buildErrorResponse(e, "Resource already exists", e.getMessage(), HttpStatus.CONFLICT, request);
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
        ErrorDetail errorDetail = new ErrorDetail(
                title,detail,httpStatus.value(),errors);

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