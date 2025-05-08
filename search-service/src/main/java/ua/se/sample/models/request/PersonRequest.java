package ua.se.sample.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    @NotBlank(message = "{person.name.not.blank}")
    @Size(max = 500, message = "{person.name.size}")
    private String name;
}
