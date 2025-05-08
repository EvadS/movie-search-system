package ua.se.sample.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    private Integer id;
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
