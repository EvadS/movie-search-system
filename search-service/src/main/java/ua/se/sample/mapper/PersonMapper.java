package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.LanguageEntity;
import ua.se.sample.dao.PersonEntity;
import ua.se.sample.models.request.LanguageRequest;
import ua.se.sample.models.request.PersonRequest;
import ua.se.sample.models.response.LanguageResponse;
import ua.se.sample.models.response.PersonResponse;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "personName")
    })
    PersonEntity toEntity(PersonRequest country);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "personName", target = "name")
    })
    PersonResponse toPersonResponse(PersonEntity entity);
}
