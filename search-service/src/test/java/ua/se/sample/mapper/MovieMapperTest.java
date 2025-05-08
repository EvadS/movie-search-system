package ua.se.sample.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.Movie;
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MovieResponseItem;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class MovieMapperTest {
    MovieMapper countryMapper = Mappers.getMapper(MovieMapper.class);

    @Test
    @DisplayName("map movie to Full Response")
    void toMovieFullInfoResponse() {

        Movie movie =  prepareBaseMovieEntity();
        MovieFullInfoResponse movieResponse = countryMapper.toMovieFullInfoResponse(movie);

        assertEquals(movie.getId(),movieResponse.getId());
        assertEquals(movie.getTitle(),movieResponse.getTitle());
        assertEquals(movie.getBudget(),movieResponse.getBudget());
        assertEquals(movie.getHomepage(),movieResponse.getHomepage());
        assertEquals(movie.getOverview(),movieResponse.getOverview());
        assertEquals(movie.getPopularity(),movieResponse.getPopularity());
        assertEquals(movie.getReleaseDate(),movieResponse.getReleaseDate());
        assertEquals(movie.getRevenue(),movieResponse.getRevenue());
        assertEquals(movie.getMovieStatus(),movieResponse.getMovieStatus());
        assertEquals(movie.getVoteAverage(),movieResponse.getVoteAverage());
        assertEquals(movie.getVoteCount(),movieResponse.getVoteCount());
    }

    @Test
    @DisplayName("map movie to Item Response")
    void toMovieResponseItem() {
        Movie movie =  prepareBaseMovieEntity();
        MovieResponseItem movieResponse = countryMapper.toMovieResponseItem(movie);

        assertEquals(movie.getId(),movieResponse.getId());
        assertEquals(movie.getTitle(),movieResponse.getTitle());
        assertEquals(movie.getBudget(),movieResponse.getBudget());
        assertEquals(movie.getHomepage(),movieResponse.getHomepage());
        assertEquals(movie.getOverview(),movieResponse.getOverview());
        assertEquals(movie.getPopularity(),movieResponse.getPopularity());
        assertEquals(movie.getReleaseDate(),movieResponse.getReleaseDate());
        assertEquals(movie.getRevenue(),movieResponse.getRevenue());
        assertEquals(movie.getMovieStatus(),movieResponse.getMovieStatus());
        assertEquals(movie.getVoteAverage(),movieResponse.getVoteAverage());
        assertEquals(movie.getVoteCount(),movieResponse.getVoteCount());
    }

    @Test
    void toMovieEntity() {
    }

    @Test
    void testToMovieEntity() {
    }

    private Movie prepareBaseMovieEntity (){

        LocalDate date = LocalDate.of(1982, 6, 25);
        return Movie.builder()
                .id(78L)
                .title("Blade Runner")
                .budget(28000000)
                .homepage("http://www.warnerbros.com/blade-runner")
                .overview("In the smog-choked dystopian Los Angeles of 2019, blade runner Rick Deckard is called out of retirement to terminate a quartet of replicants who have escaped to Earth seeking their creator for a way to extend their short life spans.")
                .popularity (BigDecimal.valueOf(94.056131))
                .releaseDate(date)
                .revenue(33139618l)
                .runtime(117)
                .movieStatus("Released")
                .tagline("Man has made his match... now it's his problem.")
                .voteAverage(BigDecimal.valueOf(7.90))
                .voteCount(3509)
                .build();
    }
}