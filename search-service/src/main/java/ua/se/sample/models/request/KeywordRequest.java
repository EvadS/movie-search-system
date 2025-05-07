package ua.se.sample.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeywordRequest {


    @NotEmpty(message = "{keyword.notempty}")
    @Size(max = 100)
    private String name;
}