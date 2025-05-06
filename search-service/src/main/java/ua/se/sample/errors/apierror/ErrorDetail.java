package ua.se.sample.errors.apierror;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetail {

  //  @ApiModelProperty( name = "Error Detail Title", required = true, example = "Resource not found")
    private String message;

  //  @ApiModelProperty(name = "Error Detail Description", required = true, example = "Requested resource cannot be found")
    private String detail;

   // @ApiModelProperty(name = "HTTP Status Code", example = "404")
    private Integer status;

   // @ApiModelProperty(name = "List of additional errors")
    private List<ApiValidationError> errors;

    private String stackTrace;

    public ErrorDetail() {
    }


    /**
     *
     * @param message error title
     * @param detail error detail
     * @param status http status
     * @param errors sub errors list
     */
    public ErrorDetail(String message, String detail, Integer status, List<ApiValidationError> errors) {
        this(message,detail,status,errors,null);
    }

    public ErrorDetail(String message, String detail, Integer status, List<ApiValidationError> errors, String stackTrace) {
        this.message = message;
        this.detail = detail;
        this.status = status;
        this.errors = errors;
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ApiValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ApiValidationError> errors) {
        this.errors = errors;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
