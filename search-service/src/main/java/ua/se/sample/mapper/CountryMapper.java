package ua.se.sample.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.CountryEntity;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;


@Mapper(componentModel = "spring")
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "text", target = "text"),
            @Mapping(source = "isoCode", target = "countryIsoCode"),
            @Mapping(source = "name", target = "countryName")
    })
    CountryEntity toEntity(CountryRequest country);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "text", target = "text"),
            @Mapping(source = "countryIsoCode", target = "isoCode"),
            @Mapping(source = "countryName", target = "name")
    })
    CountryResponse toCountryResponse(CountryEntity countryEntity);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "countryIsoCode", target = "isoCode"),
            @Mapping(source = "countryName", target = "name")
    })
    CountryResponseItem toCountryResponseItem(CountryEntity countryEntity);
}
