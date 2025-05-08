package ua.se.sample.models.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenderRequest {

    @NotBlank(message = "{gender.name.not.blank}")
    @Size(max = 20, message = "{gender.name.size}")
    private String name;
}