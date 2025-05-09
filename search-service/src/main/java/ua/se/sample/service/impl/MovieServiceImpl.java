package ua.se.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.se.sample.dao.CountryEntity;
import ua.se.sample.dao.Movie;
import ua.se.sample.dao.ProductionCountry;
import ua.se.sample.dao.ProductionCountryPK;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.MovieMapper;
import ua.se.sample.models.request.MovieFullInfoRequest;
import ua.se.sample.models.request.MovieRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MoviePagedResponse;
import ua.se.sample.models.response.MovieResponseItem;
import ua.se.sample.repository.CountryRepository;
import ua.se.sample.repository.MovieRepository;
import ua.se.sample.repository.ProductionCountryRepository;
import ua.se.sample.service.MovieService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final ProductionCountryRepository productionCountryRepository;
    private final MovieMapper mapper;

    private static MoviePagedResponse toPagedMoviePagedResponse(List<MovieResponseItem> list, Page<Movie> all) {
        return new MoviePagedResponse()
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
    public List<MovieResponseItem> getMoviesList(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Movie> all = movieRepository.findAll(pageable);

        return all.getContent().stream()
                .map(mapper::toMovieResponseItem).toList();
    }

    @Override
    public MoviePagedResponse getPagedMovies(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<Movie> all = movieRepository.findAll(pageable);

        List<MovieResponseItem> list = all.stream().map(mapper::toMovieResponseItem).toList();

        MoviePagedResponse response = toPagedMoviePagedResponse(list, all);
        return response;
    }

    @Override
    public MovieFullInfoResponse getMovieFullInfo(Long id) {
        return movieRepository.findById(id)
                .map(mapper::toMovieFullInfoResponse)
                .orElseThrow(() -> new ResourceNotFoundException("movie", "id", id));
    }

    @Transactional
    @Override
    public MovieFullInfoResponse createNewMovie(MovieRequest movieRequest) {
        Movie movie = mapper.toMovieEntity(movieRequest);
        movie = movieRepository.save(movie);

        if(movieRequest.getCountryId() !=null){
            //countryId
            CountryEntity countryEntity =countryRepository.findById(movieRequest.getCountryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Country", "id", movieRequest.getCountryId()));

            ProductionCountryPK productionCountryPK = new ProductionCountryPK();
            productionCountryPK.setMovie(movie);
            productionCountryPK.setCountry(countryEntity);

            ProductionCountry productionCountry = new ProductionCountry();
            productionCountry.setProductionCountryPK(productionCountryPK);
            productionCountryRepository.save(productionCountry);
        }

        return mapper.toMovieFullInfoResponse(movie);
    }

    @Override
    public MovieFullInfoResponse updateMovie(Long id, MovieRequest movieRequest) {
        Movie movieEntity = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));

        movieEntity.setBudget(movieRequest.getBudget());

        if (movieRequest.getBudget() != null)
            movieEntity.setBudget(movieRequest.getBudget());

        if (movieRequest.getTitle() != null)
            movieEntity.setTitle(movieRequest.getTitle());

        if (movieRequest.getHomepage() != null)
            movieEntity.setHomepage(movieRequest.getHomepage());

        if (movieRequest.getOverview() != null)
            movieEntity.setOverview(movieRequest.getOverview());

        if (movieRequest.getPopularity() != null)
            movieEntity.setPopularity(movieRequest.getPopularity());

        if (movieRequest.getReleaseDate() != null)
            movieEntity.setReleaseDate(movieRequest.getReleaseDate());

        if (movieRequest.getRevenue() != null)
            movieEntity.setRevenue(movieRequest.getRevenue());

        if (movieRequest.getRuntime() != null)
            movieEntity.setTitle(movieRequest.getTitle());

        if (movieRequest.getMovieStatus() != null)
            movieEntity.setMovieStatus(movieRequest.getMovieStatus());

        if (movieRequest.getTagline() != null)
            movieEntity.setTagline(movieRequest.getTagline());

        if (movieRequest.getVoteAverage() != null)
            movieEntity.setVoteAverage(movieRequest.getVoteAverage());

        if (movieRequest.getVoteCount() != null)
            movieEntity.setVoteCount(movieRequest.getVoteCount());

        if(movieRequest.getCountryId() !=null){
            //countryId
            CountryEntity countryEntity =countryRepository.findById(movieRequest.getCountryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Country", "id", movieRequest.getCountryId()));

            ProductionCountryPK productionCountryPK = new ProductionCountryPK();
            productionCountryPK.setMovie(movieEntity);
            productionCountryPK.setCountry(countryEntity);

            ProductionCountry productionCountry = new ProductionCountry();
            productionCountry.setProductionCountryPK(productionCountryPK);
            productionCountryRepository.save(productionCountry);
        }


        movieEntity = movieRepository.save(movieEntity);
        return mapper.toMovieFullInfoResponse(movieEntity);
    }
}
