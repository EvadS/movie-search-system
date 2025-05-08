package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.GenderEntity;
import ua.se.sample.dao.GenreEntity;
import ua.se.sample.models.request.GenderRequest;
import ua.se.sample.models.request.GenreRequest;
import ua.se.sample.models.response.GenderResponse;
import ua.se.sample.models.response.GenreResponse;


@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "genreName")
    })
    GenreEntity toEntity(GenreRequest request);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "genreName", target = "name")
    })
    GenreResponse toGenreResponse(GenreEntity countryEntity);
}
