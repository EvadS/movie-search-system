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
public class CompanyRequest {

    @NotBlank(message = "{company.name.not.empty}")
    @Size(max = 200, message = "{company.name.size}")
    private String name;
}
