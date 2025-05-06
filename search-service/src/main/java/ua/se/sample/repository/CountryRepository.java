package ua.se.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.se.sample.dao.CountryEntity;

import java.util.Optional;

@Repository
public interface CountryRepository  extends JpaRepository<CountryEntity, Long> {

    Optional<CountryEntity> findByCountryName(String countryName);

    Optional<CountryEntity> findByCountryIsoCode(String isoCode);
}