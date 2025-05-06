package ua.se.sample.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.CountryEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.CountryMapper;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;
import ua.se.sample.repository.CountryRepository;
import ua.se.sample.service.CountryService;

import java.util.List;


@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;
    private final CountryMapper countryMapper;

    @Override
    public  List<CountryResponseItem> getCountryList() {

        return repository.findAll()
                .stream()
                .map(countryMapper::toCountryResponseItem)
                .toList();
    }

    @Override
    public CountryResponseItem getCountryByName(String countryName) {

        CountryEntity countryEntity = repository.findByCountryName(countryName)
                .orElseThrow(() -> new ResourceNotFoundException("country", "name", countryName));

        return  countryMapper.toCountryResponseItem(countryEntity);
    }

    @Override
    public CountryResponse createCountry(CountryRequest country) {

        CountryEntity countryEntity = countryMapper.toEntity(country);
        countryEntity = repository.save(countryEntity);
        return countryMapper.toCountryResponse(countryEntity);
    }

    @Override
    public CountryResponse updateCountry(Long id, CountryRequest country) {

        CountryEntity countryEntity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("country", "id", id));

        countryEntity.setCountryName(country.getName());
        countryEntity.setText(country.getText());
        countryEntity.setCountryIsoCode(country.getIsoCode());

        countryEntity = repository.save(countryEntity);
        return countryMapper.toCountryResponse(countryEntity);
    }

    @Override
    public void deleteCountry(Long id) {
        repository.deleteById(id);
    }
}
