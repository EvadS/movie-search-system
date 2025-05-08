package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.KeywordEntity;
import ua.se.sample.dao.LanguageEntity;
import ua.se.sample.models.request.KeywordRequest;
import ua.se.sample.models.request.LanguageRequest;
import ua.se.sample.models.response.KeywordResponse;
import ua.se.sample.models.response.LanguageResponse;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "languageName"),
            @Mapping(source = "code", target = "languageCode")
    })
    LanguageEntity toEntity(LanguageRequest country);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "languageName", target = "name"),
            @Mapping(source = "languageCode", target = "code")
    })
    LanguageResponse toLanguageResponse(LanguageEntity countryEntity);
}
