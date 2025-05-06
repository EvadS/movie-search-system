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
public class CountryRequest {
    @NotBlank
    @Size(max = 40)
    private String text;

    @NotBlank
    @Size(max = 10)
    public String isoCode;

    @Size(max = 100)
    public String name;
}
