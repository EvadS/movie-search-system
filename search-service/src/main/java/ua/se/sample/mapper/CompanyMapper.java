package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.ProductionCompanyEntity;
import ua.se.sample.models.request.CompanyRequest;
import ua.se.sample.models.response.CompanyResponse;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "companyName")
    })
    ProductionCompanyEntity toEntity(CompanyRequest country);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "companyName", target = "name")
    })
    CompanyResponse toCompanyResponse(ProductionCompanyEntity countryEntity);
}
