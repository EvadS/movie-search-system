package ua.se.sample.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.se.sample.dao.CountryEntity;
import ua.se.sample.mapper.CountryMapper;
import ua.se.sample.mapper.CountryMapperImpl;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;
import ua.se.sample.repository.CountryRepository;
import ua.se.sample.service.impl.CountryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class CountryServiceTest {

    private static final String ISO = "iso";
    private final Long defaultId = 1L;
    private final String COUNTRY_NAME = "Veyshnoria";

    @Spy
    private CountryMapper categoryRequestMapper = new CountryMapperImpl();

    @Mock
    private CountryRepository repository;

    @InjectMocks
    private CountryServiceImpl countryService;

    @Test
    void getCountryList() {
        Optional<CountryEntity> countryOpt = Optional.of(prepareCountryEntity(COUNTRY_NAME));
        // Mock the behavior of the repository to return the mock employee
        //Mockito.when(repository.findByCountryName(countryName)).thenReturn(countryEntity);
    }

    @Test
    public void testAddCountryItem() {
        try {
            CountryEntity countryEntity = prepareCountryEntity(COUNTRY_NAME);
            countryEntity.setId(1L);
            CountryRequest countryRequest = generateCountryRequest(COUNTRY_NAME);

            given(repository.save(any())).willReturn(countryEntity);

            CountryResponse countryResponse = countryService.createCountry(countryRequest);

            Assertions.assertEquals(countryEntity.getId(), countryResponse.getId());
            Assertions.assertEquals(countryEntity.countryName,countryResponse.getName());
            Assertions.assertEquals(countryEntity.getCountryIsoCode(), countryResponse.getIsoCode());
        }
        catch(Exception e) {
            Assertions.fail("Error occurred while creating catalogue item", e);
        }
    }


    @Test
    void getCountryByName() {
    }

    @Test
    void createCountry() {
    }

    @Test
    void updateCountry() {
    }

    @Test
    void deleteCountry() {
    }

    private List<CountryResponseItem> prepareCountryResponse() {
        final List<CountryResponseItem> catalogueItems = new ArrayList<>();
        final Random random = new Random();

        IntStream
                .rangeClosed(1, 10)
                .forEach(i -> {
                    catalogueItems.add(
                            prepareCountryResponseItem((long) i,
                                    "country-" + random.ints(1000, 9999).findFirst().getAsInt())
                    );
                });

        return catalogueItems;
    }

    private CountryResponseItem prepareCountryResponseItem(Long i, String s) {

        return CountryResponseItem.builder()
                .id(i)
                .isoCode(ISO)
                .name(s).build();
    }


    private CountryEntity prepareCountryEntity(String countryName) {
        CountryEntity entity = new CountryEntity();
        entity.setId(1L);
        entity.setCountryName(countryName);
        entity.setCountryIsoCode(ISO);
        entity.setText("Australia is a federal parliamentary democracy and constitutional monarchy comprising six states and ten territories. Its population of almost 28 million is ...");

        return entity;
    }

    private CountryRequest generateCountryRequest(String countryName) {
        return CountryRequest.builder()
                .isoCode("AU")
                .name(countryName)
                .text("is a federal parliamentary democracy and constitutional monarchy comprising six states and ten territories. Its population of almost 28 million is ...")
                .build();
    }
}