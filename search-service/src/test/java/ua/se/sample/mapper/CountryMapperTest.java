package ua.se.sample.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import ua.se.sample.dao.CountryEntity;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;


class CountryMapperTest {
    CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);

    @Test
    public void countryToEntityTest() {
        //given
        CountryRequest request = generateCountryRequest();
        CountryEntity entity = generateCountryEntity();

        //when
        CountryEntity mapperEntity = countryMapper.toEntity(request);

        //then
        Assertions.assertNotNull(mapperEntity);
        Assertions.assertEquals(mapperEntity.getCountryName(), entity.getCountryName());
        Assertions.assertEquals(mapperEntity.getCountryIsoCode(), entity.getCountryIsoCode());
        Assertions.assertEquals(mapperEntity.getText(), entity.getText());
    }

    @Test
    public void entityToCountryResponse() {
        //given
        CountryEntity entity = generateCountryEntity();
        CountryResponse request = generateCountryResponse(entity);
        //when
        CountryResponse countryResponse = countryMapper.toCountryResponse(entity);

        //then
        Assertions.assertNotNull(countryResponse);
        Assertions.assertEquals(countryResponse.getId(), entity.getId());
        Assertions.assertEquals(countryResponse.getName(), entity.getCountryName());
        Assertions.assertEquals(countryResponse.getIsoCode(), entity.getCountryIsoCode());
        Assertions.assertEquals(countryResponse.getText(), entity.getText());
    }

    @Test
    public void toCountryItemList() {

    }

    private CountryRequest generateCountryRequest() {
        return CountryRequest.builder()
                .isoCode("AU")
                .name("Australia")
                .text("Australia is a federal parliamentary democracy and constitutional monarchy comprising six states and ten territories. Its population of almost 28 million is ...")
                .build();
    }

    private CountryResponse generateCountryResponse(CountryEntity entity) {
        return CountryResponse.builder()
                .id(1L)
                .isoCode(entity.getCountryIsoCode())
                .name(entity.getCountryName())
                .text(entity.getText())
                .build();
    }

    private CountryEntity generateCountryEntity() {
        CountryEntity entity = new CountryEntity();
        entity.setId(1L);
        entity.setCountryName("Australia");
        entity.setCountryIsoCode("AU");
        entity.setText("Australia is a federal parliamentary democracy and constitutional monarchy comprising six states and ten territories. Its population of almost 28 million is ...");

        return entity;

    }
}