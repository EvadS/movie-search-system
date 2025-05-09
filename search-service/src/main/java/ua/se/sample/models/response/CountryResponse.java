package ua.se.sample.models.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "updated country", description = "provide information about updated country")
public class CountryResponse {

    @Schema(name="id", description= "unique identifier")
    private Long id;

    @Schema(name="text",description = "updated text")
    private String text;

    @Schema(name="isoCode",description = "updated iso code")
    public String isoCode;

    @Schema(name="text",description = "updated name")
    public String name;
}
