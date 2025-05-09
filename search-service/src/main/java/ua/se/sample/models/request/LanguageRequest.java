package ua.se.sample.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LanguageRequest {

    @NotBlank(message = "{language.name.not.blank}")
    @Size(max = 500, message = "{language.name.size}")
    private String name;

    @NotBlank(message = "{language.code.not.blank}")
    @Size(max = 10, message = "{language.code.size}")
    private String code;
}