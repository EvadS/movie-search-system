package ua.se.sample.web;


import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;
import ua.se.sample.errors.apierror.ApiValidationError;
import ua.se.sample.errors.apierror.ErrorDetail;

import static org.assertj.core.api.Java6Assertions.*;

public class ResponseBodyMatchers {

  private ObjectMapper objectMapper = new ObjectMapper();

  public <T> ResultMatcher containsObjectAsJson(Object expectedObject, Class<T> targetClass) {
    return mvcResult -> {
      String json = mvcResult.getResponse().getContentAsString();
      T actualObject = objectMapper.readValue(json, targetClass);
      assertThat(actualObject).isEqualToComparingFieldByField(expectedObject);
    };
  }

  public ResultMatcher containsErrors(String expectedFieldName, String expectedMessage) {
    return mvcResult -> {
      String json = mvcResult.getResponse().getContentAsString();
      ErrorDetail errorResult = objectMapper.readValue(json, ErrorDetail.class);

      List<ApiValidationError> fieldErrors = errorResult.getErrors().stream()
              .filter(fieldError -> fieldError.getField().equals(expectedFieldName))
              .filter(fieldError -> fieldError.getMessage().equals(expectedMessage))
              .collect(Collectors.toList());

      assertThat(fieldErrors)
              .hasSize(1)
              .withFailMessage("expecting exactly 1 error message with field name '%s' and message '%s'",
                      expectedFieldName,
                      expectedMessage);
    };
  }

  public static ResponseBodyMatchers responseBody() {
    return new ResponseBodyMatchers();
  }

}
