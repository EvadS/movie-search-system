package ua.se.sample.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "country update request", description = "provide information to update exists country")
public class CountryRequest {

    @Schema(description = "value to update country text")
    @NotBlank(message = "{country.text.not.blank}")
    @Size(max = 1000, message = "{country.text.size}")
    private String text;

    @Schema(description = "value to update country iso code")
    @NotBlank(message = "{country.iso.code.not.blank}")
    @Size(max = 10, message = "{country.iso.code.size}")
    public String isoCode;

    @Schema(description = "value to update country name")
    @NotBlank(message = "{country.name.not.blank}")
    @Size(max = 100, message = "{country.name.size}")
    public String name;
}