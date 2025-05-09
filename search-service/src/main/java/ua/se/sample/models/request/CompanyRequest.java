package ua.se.sample.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="model to update country" ,description="provide information to update country")
public class CompanyRequest {

    @Schema(description = "value to update company name")
    @NotBlank(message = "{company.name.not.empty}")
    @Size(max = 200, message = "{company.name.size}")
    private String name;

}
