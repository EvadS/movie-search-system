package ua.se.sample.models.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageRequest {

    @NotBlank(message = "Please provide a language name")
    @Size(max = 500,message = "No more 100 symbols")
    private String name;

    @NotBlank(message = "Please provide a language name")
    @Size(max =10 ,message = "No more 100 symbols")
    private String code;

}