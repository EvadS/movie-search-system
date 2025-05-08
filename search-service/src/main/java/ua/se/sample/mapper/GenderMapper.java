package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.GenderEntity;
import ua.se.sample.models.request.GenderRequest;
import ua.se.sample.models.response.GenderResponse;


@Mapper(componentModel = "spring")
public interface GenderMapper {

    GenderMapper INSTANCE = Mappers.getMapper(GenderMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "gender")
    })
    GenderEntity toEntity(GenderRequest request);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "gender", target = "name")
    })
    GenderResponse toGenderResponse(GenderEntity countryEntity);
}
