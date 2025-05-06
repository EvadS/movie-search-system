package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;

import java.util.List;

public interface CountryService {
    List<CountryResponseItem> getCountryList();

    CountryResponseItem getCountryByName(String countryName);

    CountryResponse createCountry(@Valid CountryRequest catalogueItem);

    CountryResponse updateCountry(Long id , @Valid CountryRequest country);

    void deleteCountry(Long id);
}
