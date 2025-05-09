package ua.se.sample.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import ua.se.sample.models.request.MovieFullInfoRequest;
import ua.se.sample.models.request.MovieRequest;
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MoviePagedResponse;
import ua.se.sample.models.response.MovieResponseItem;

import java.util.List;

public interface MovieService {

    List<MovieResponseItem> getMoviesList(int pageNum, int pageSize);
    MoviePagedResponse getPagedMovies(int pageNum, int pageSize);

    MovieFullInfoResponse getMovieFullInfo(Long id);

    MovieFullInfoResponse add(MovieRequest request);
    MovieFullInfoResponse addFull(MovieFullInfoRequest request);

    MovieFullInfoResponse createNewMovie(@NotNull @Valid MovieRequest movieRequest);

    MovieFullInfoResponse updateMovie(@NotNull Long id, @NotNull @Valid MovieRequest movieRequest);
}
