package ua.se.sample.models.request;

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

    @NotBlank(message = "{language.name.not.blank}")
    @Size(max = 500, message = "{language.name.size}")
    private String name;

    @NotBlank(message = "{language.code.not.blank}")
    @Size(max = 10, message = "{language.code.size}")
    private String code;
}