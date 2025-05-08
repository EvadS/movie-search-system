package ua.se.sample.models.request;

import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest {

    @Size(max = 100)
    private String name;
}