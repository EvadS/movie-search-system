package ua.se.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.se.sample.dao.*;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.generated.LanguageRole;
import ua.se.sample.generated.MovieGenre;
import ua.se.sample.generated.MovieGenrePK;
import ua.se.sample.mapper.MovieMapper;
import ua.se.sample.models.request.MovieRequest;
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MoviePagedResponse;
import ua.se.sample.models.response.MovieResponseItem;
import ua.se.sample.repository.*;
import ua.se.sample.service.MovieService;

import java.util.List;
import ua.se.sample.config.AppConst;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final ProductionCountryRepository productionCountryRepository;
    private final GenreRepository genreRepository;

    private final MovieGenreRepository movieGenreRepository;

    private final LanguageRepository languageRepository;
    private final MovieLanguageRepository movieLanguageRepository;
private final  LanguageRoleRepository languageRoleRepository;

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

        //languageId
        if(movieRequest.getLanguageId()!=null){
            LanguageEntity languageEntity = languageRepository.findById(movieRequest.getLanguageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Language", "id", movieRequest.getLanguageId()));

            LanguageRole  languageRole = languageRoleRepository.findById(AppConst.DEFAULT_LANGUAGE_ROLE_ID)
                    .orElseThrow(() -> new ResourceNotFoundException(" movie Language", "id", AppConst.DEFAULT_LANGUAGE_ROLE_ID));

            // build relation
            MovieLanguagePK movieLanguagePK = new MovieLanguagePK();

            movieLanguagePK.setMovie(movie);
            movieLanguagePK.setLanguage(languageEntity);
            movieLanguagePK.setLanguageRole(languageRole);

            MovieLanguage movieLanguage = new MovieLanguage();
            movieLanguage.setMovieLanguagePK(movieLanguagePK);
            movieLanguageRepository.save(movieLanguage);
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
            CountryEntity countryEntity =countryRepository.findById(movieRequest.getCountryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Country", "id", movieRequest.getCountryId()));

            ProductionCountryPK productionCountryPK = new ProductionCountryPK();
            productionCountryPK.setMovie(movieEntity);
            productionCountryPK.setCountry(countryEntity);

            ProductionCountry productionCountry = new ProductionCountry();
            productionCountry.setProductionCountryPK(productionCountryPK);
            productionCountryRepository.save(productionCountry);
        }

        if(movieRequest.getGenreId()!=null){
            GenreEntity genreEntity = genreRepository.findById(movieRequest.getGenreId())
                    .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", movieRequest.getGenreId()));

            // build relation
            MovieGenrePK movieGenrePK = new MovieGenrePK();
            movieGenrePK.setMovie(movieEntity);
            movieGenrePK.setGenre(genreEntity);

            MovieGenre movieGenre = new MovieGenre();
            movieGenre.setOrderPK(movieGenrePK);

            movieGenreRepository.save(movieGenre);
        }

        //languageId
        if(movieRequest.getLanguageId()!=null){
            LanguageEntity languageEntity = languageRepository.findById(movieRequest.getLanguageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Language", "id", movieRequest.getLanguageId()));

            LanguageRole  languageRole = languageRoleRepository.findById(AppConst.DEFAULT_LANGUAGE_ROLE_ID)
                    .orElseThrow(() -> new ResourceNotFoundException(" movie Language", "id", AppConst.DEFAULT_LANGUAGE_ROLE_ID));

            // build relation
            MovieLanguagePK movieLanguagePK = new MovieLanguagePK();

            movieLanguagePK.setMovie(movieEntity);
            movieLanguagePK.setLanguage(languageEntity);
            movieLanguagePK.setLanguageRole(languageRole);

            MovieLanguage movieLanguage = new MovieLanguage();
            movieLanguage.setMovieLanguagePK(movieLanguagePK);
            movieLanguageRepository.save(movieLanguage);
        }

        movieEntity = movieRepository.save(movieEntity);
        return mapper.toMovieFullInfoResponse(movieEntity);
    }
}
