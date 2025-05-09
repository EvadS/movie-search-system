package ua.se.sample.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeywordRequest {

    @NotBlank(message = "{keyword.name.size}")
    @Size(max = 100, message = "{keyword.name.not.blank}")
    private String name;
}