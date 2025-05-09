package ua.se.sample.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.KeywordRequest;
import ua.se.sample.models.response.KeywordResponse;
import ua.se.sample.service.KeywordService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.KEYWORD_PATH)
@AllArgsConstructor
@Tag(name = "Keyword", description = "Keyword management APIs")
public class KeywordController {

    private final KeywordService keywordService;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<KeywordResponse> getList() {
        return keywordService.list();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    public KeywordResponse getById(@PathVariable(value = "id") Long id) {
        return keywordService.getById(id);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public KeywordResponse add(@Valid @RequestBody KeywordRequest keywordRequest) {
        return keywordService.create(keywordRequest);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public KeywordResponse update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody KeywordRequest keywordRequest, HttpRequest request) throws ResourceNotFoundException {
        return keywordService.update(id, keywordRequest);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        keywordService.delete(id);
    }
}
