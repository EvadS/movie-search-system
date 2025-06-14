package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.Movie;
import ua.se.sample.models.request.MovieFullInfoRequest;
import ua.se.sample.models.request.MovieRequest;
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MovieResponseItem;


@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mappings({
//            @Mapping(target = "id", ignore = true),
            @Mapping(target = "id", source = "id"),
    })
    MovieFullInfoResponse toMovieFullInfoResponse(Movie movie);

    Movie toMovieEntity(MovieRequest request);

    Movie toMovieEntity(MovieFullInfoRequest request);

    MovieResponseItem toMovieResponseItem(Movie entity);

    Movie toMovie (MovieRequest movieRequest);
}