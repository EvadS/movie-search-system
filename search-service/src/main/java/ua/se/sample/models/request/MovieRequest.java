package ua.se.sample.models.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name="movie model", description="Provide movie model")
public class MovieRequest {

    @Schema(description = "Name of the movie.",
            example = "Jessica Abigail", required = true)
    private String title;

    @Schema(description = "Name of the movie.",
            example = "100")
    private Integer budget;

    private String homepage;
    private String overview;
    private BigDecimal popularity;
    private LocalDate releaseDate;
    private Long revenue;

    private Integer runtime;
    private String movieStatus;
    private String tagline;
    private BigDecimal voteAverage;
    private Integer voteCount;

    private Long countryId;
}
