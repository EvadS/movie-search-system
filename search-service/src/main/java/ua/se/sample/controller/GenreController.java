package ua.se.sample.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.GenreRequest;
import ua.se.sample.models.response.GenreResponse;
import ua.se.sample.service.GenreService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.GENRE_PATH)
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<GenreResponse> getList() {
        return genreService.list();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    public GenreResponse getById(@PathVariable(value = "id") Long id) {
        return genreService.getById(id);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public GenreResponse add(@Valid @RequestBody GenreRequest genreRequest) {
        return genreService.create(genreRequest);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public GenreResponse update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody GenreRequest genreRequest) {
        return  genreService.update(id, genreRequest);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        genreService.delete(id);
    }
}
