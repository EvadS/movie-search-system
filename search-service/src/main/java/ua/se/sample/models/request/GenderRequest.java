package ua.se.sample.models.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenderRequest {

    private String gender;

}