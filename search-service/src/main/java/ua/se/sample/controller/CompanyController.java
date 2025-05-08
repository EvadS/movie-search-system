package ua.se.sample.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.models.request.CompanyRequest;
import ua.se.sample.models.response.CompanyResponse;
import ua.se.sample.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.COMPANY_PATH)
@AllArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    public List<CompanyResponse> getList() {
        return service.list();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    public CompanyResponse getById(@PathVariable(value = "id") Long id) {
        return service.getById(id);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompanyResponse add(@Valid @RequestBody CompanyRequest companyRequest) {
        return service.create(companyRequest);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public CompanyResponse update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody CompanyRequest companyRequest)  {
        return service.update(id, companyRequest);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remove(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }
}
