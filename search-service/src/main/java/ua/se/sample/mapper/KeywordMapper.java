package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.KeywordEntity;
import ua.se.sample.models.request.KeywordRequest;
import ua.se.sample.models.response.KeywordResponse;

@Mapper(componentModel = "spring")
public interface KeywordMapper {
    KeywordMapper INSTANCE = Mappers.getMapper(KeywordMapper.class);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "keywordName")
    })
    KeywordEntity toEntity(KeywordRequest country);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "keywordName", target = "name")
    })
    KeywordResponse toKeywordResponse(KeywordEntity countryEntity);
}
