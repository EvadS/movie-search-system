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

    @NotBlank(message = "{country.text.not.blank}")
    @Size(max = 1000, message = "{country.text.size}")
    private String text;

    @NotBlank(message = "{country.iso.code.not.blank}")
    @Size(max = 10, message = "{country.iso.code.size}")
    public String isoCode;

    @NotBlank(message = "{country.name.not.blank}")
    @Size(max = 100, message = "{country.name.size}")
    public String name;
}