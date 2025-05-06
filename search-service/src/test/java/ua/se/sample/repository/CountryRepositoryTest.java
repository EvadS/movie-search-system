package ua.se.sample.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ua.se.sample.dao.CountryEntity;
import ua.se.sample.models.request.CountryRequest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@Log4j2
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public  class CountryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CountryRepository repository;

    private final String COUNTRY_NAME = "Veyshnoria";

    @BeforeAll
    static void setup() {
        log.info("@BeforeAll - executes once before all test methods in this class");
    }

    private CountryEntity prepareCountryEntity() {
        CountryEntity entity = new CountryEntity();
        entity.setId(1L);
        entity.setCountryName("Veyshnoria");
        entity.setCountryIsoCode("VSN");
        entity.setText("FAKE COUNTRY");

        return entity;
    }
}