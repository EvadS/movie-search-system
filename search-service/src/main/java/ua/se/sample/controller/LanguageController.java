package ua.se.sample.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.KeywordRequest;
import ua.se.sample.models.request.LanguageRequest;
import ua.se.sample.models.response.KeywordResponse;
import ua.se.sample.models.response.LanguageResponse;
import ua.se.sample.service.KeywordService;
import ua.se.sample.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.LANGUAGE_PATH)
@AllArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<LanguageResponse> getList() {
        return languageService.list();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    public LanguageResponse getById(@PathVariable(value = "id") Long id) {
        return languageService.getById(id);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public LanguageResponse add(@Valid @RequestBody LanguageRequest languageRequest) {
        return languageService.create(languageRequest);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public LanguageResponse update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody LanguageRequest languageRequest )  {

        return languageService.update(id, languageRequest);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        languageService.delete(id);
    }
}
