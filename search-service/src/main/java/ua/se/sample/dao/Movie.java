package ua.se.sample.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Movie {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 1000)
    @Column(name = "title", length = 1000)
    private String title;

    @Column(name = "budget")
    private Integer budget;

    @Size(max = 1000)
    @Column(name = "homepage", length = 1000)
    private String homepage;

    @Size(max = 1000)
    @Column(name = "overview", length = 1000)
    private String overview;

    @Column(name = "popularity", precision = 12, scale = 6)
    private BigDecimal popularity;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "revenue")
    private Long revenue;

    @Column(name = "runtime")
    private Integer runtime;

    @Size(max = 50)
    @Column(name = "movie_status", length = 50)
    private String movieStatus;

    @Size(max = 1000)
    @Column(name = "tagline", length = 1000)
    private String tagline;

    @Column(name = "vote_average", precision = 4, scale = 2)
    private BigDecimal voteAverage;

    @Column(name = "vote_count")
    private Integer voteCount;

}