package ua.se.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.Movie;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.MovieMapper;
import ua.se.sample.models.request.MovieFullInfoRequest;
import ua.se.sample.models.request.MovieRequest;
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MoviePagedResponse;
import ua.se.sample.models.response.MovieResponseItem;
import ua.se.sample.repository.MovieRepository;
import ua.se.sample.service.MovieService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    @Override
    public List<MovieResponseItem> getMoviesList(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Movie> all = repository.findAll(pageable);

        return all.getContent().stream()
                .map(mapper::toMovieResponseItem).toList();
    }

    @Override
    public MoviePagedResponse getPagedMovies(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Movie> all = repository.findAll(pageable);

        List<MovieResponseItem> list = all.stream().map(mapper::toMovieResponseItem).toList();

        MoviePagedResponse response = toPagedMoviePagedResponse(list, all);
        return response;
    }

    private static MoviePagedResponse toPagedMoviePagedResponse(List<MovieResponseItem> list, Page<Movie> all) {
        return  new MoviePagedResponse()
                .toBuilder()
                .items(list)
                .totalPages(all.getTotalPages())
                .totalElements(all.getTotalElements())
                .pageSize(all.getSize())
                .pageElements(all.getNumberOfElements())
                .items(list)
                .build();
    }

    @Override
    public MovieFullInfoResponse getMovieFullInfo(Long id) {
        return repository.findById(id)
                .map(mapper::toMovieFullInfoResponse)
                .orElseThrow(() -> new ResourceNotFoundException("movie", "id", id));

    }

    @Override
    public MovieFullInfoResponse add(MovieRequest request) {

        Movie movie = mapper.toMovieEntity(request);
        return null;
    }

    @Override
    public MovieFullInfoResponse addFull(MovieFullInfoRequest request) {
        Movie movie = mapper.toMovieEntity(request);
        return null;
    }
}
