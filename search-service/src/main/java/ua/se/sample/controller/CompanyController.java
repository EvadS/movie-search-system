package ua.se.sample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Company", description = "Company management APIs")
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
    @Operation(summary = "Update a country",
            description = "Update an existing country. The response is updated Country object.")
    public CompanyResponse update(
            @PathVariable(value = "id") Long id,
            @Valid @RequestBody CompanyRequest companyRequest)  {
        return service.update(id, companyRequest);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class))}),
            @ApiResponse(responseCode = "404", description = "Entity not found",
                    content = @Content)})
    public void remove(
            @Parameter(
                    description = "ID of company to be deleted",
                    required = true)
            @PathVariable(value = "id") Long id) {
        service.delete(id);
    }
}
