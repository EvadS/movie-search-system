package ua.se.sample.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.GenderRequest;
import ua.se.sample.models.response.GenderResponse;
import ua.se.sample.service.GenderService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.GENDER_PATH)
@AllArgsConstructor
@Tag(name = "Gender", description = "Gender management APIs")
public class GenderController {

    private final GenderService genderService;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<GenderResponse> getList() {
        return genderService.list();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    public GenderResponse getById(@PathVariable(value = "id") Long id) {
        return genderService.getById(id);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public GenderResponse add(@Valid @RequestBody GenderRequest genderRequest) {
        return genderService.create(genderRequest);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public GenderResponse update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody GenderRequest genderRequest) throws ResourceNotFoundException {
        return genderService.update(id, genderRequest);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        genderService.delete(id);
    }
}
