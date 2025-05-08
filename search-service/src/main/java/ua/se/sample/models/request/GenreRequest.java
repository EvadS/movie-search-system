package ua.se.sample.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest {

    @NotBlank(message = "{genre.name.not.blank}")
    @Size(max = 100, message = "{genre.name.size}")
    private String name;
}