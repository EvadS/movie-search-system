package ua.se.sample.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.exceptions.FileStorageException;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;
import ua.se.sample.service.CountryService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.COUNTRY_PATH)
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CountryResponseItem> getCountries() {
        return countryService.getCountryList();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_NAME)
    @ResponseStatus(value = HttpStatus.OK)
    public CountryResponseItem getGetCountryByName(@PathVariable(value = "name") String countryName) {
        return countryService.getCountryByName(countryName);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<CountryResponse> addCountry(@Valid @RequestBody CountryRequest catalogueItem) {

        CountryResponse country = countryService.createCountry(catalogueItem);
        return new ResponseEntity<>(country, HttpStatus.CREATED);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateCountry(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody CountryRequest country)  {

        countryService.updateCountry(id, country);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void removeCountry(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        countryService.deleteCountry(id);
    }

    @PatchMapping(ControllersApiPaths.UPLOAD_IMAGE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void uploadCountryImage(
            @PathVariable(value = "id") String skuNumber,
            @RequestParam("file") MultipartFile file)
            throws ResourceNotFoundException {
    }
}
