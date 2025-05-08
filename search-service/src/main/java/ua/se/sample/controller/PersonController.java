package ua.se.sample.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.PersonRequest;
import ua.se.sample.models.response.PersonResponse;
import ua.se.sample.service.PersonService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.PERSON_PATH)
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @ResponseBody
    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<PersonResponse> getList() {
        return personService.list();
    }

    @ResponseBody
    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    public PersonResponse getById(@PathVariable(value = "id") Long id) {
        return personService.getById(id);
    }

    @ResponseBody
    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public PersonResponse add(@Valid @RequestBody PersonRequest personRequest) {
        return personService.create(personRequest);
    }

    @ResponseBody
    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public PersonResponse update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody PersonRequest personRequest) {
        return personService.update(id, personRequest);
    }

    @ResponseBody
    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        personService.delete(id);
    }
}
