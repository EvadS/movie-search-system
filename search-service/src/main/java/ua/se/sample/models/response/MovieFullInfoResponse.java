package ua.se.sample.models.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieFullInfoResponse {

    @Schema(description = "Unique identifier.",
            example = "1", required = true)
    private Long id;

    private String title;

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
}
