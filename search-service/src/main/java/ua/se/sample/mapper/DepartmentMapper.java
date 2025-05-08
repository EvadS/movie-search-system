package ua.se.sample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.DepartmentEntity;
import ua.se.sample.dao.KeywordEntity;
import ua.se.sample.models.request.DepartmentRequest;
import ua.se.sample.models.response.DepartmentResponse;


@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);


    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "departmentName", target = "departmentName")
    })
    DepartmentEntity toEntity(DepartmentRequest request);


    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "departmentName", target = "name")
    })
    DepartmentResponse toDepartmentResponse(DepartmentEntity countryEntity);
}
