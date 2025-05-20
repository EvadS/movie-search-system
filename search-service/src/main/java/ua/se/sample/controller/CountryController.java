package ua.se.sample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.apierror.ErrorDetail;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;
import ua.se.sample.service.CountryService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Log4j2
@Validated
@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.COUNTRY_PATH)
@AllArgsConstructor
@Tag(name = "country", description = "Country management APIs")
public class CountryController {

    private final CountryService countryService;

    @GetMapping(ControllersApiPaths.GET_ITEMS)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "get available countries", description = "returns available country list",
            tags = {"country"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "successful operation",
                    content = @Content(mediaType = "application/json", array = @ArraySchema(
                            schema = @Schema(implementation = CountryResponseItem.class))))
    })
    public List<CountryResponseItem> getCountries() {
        return countryService.getCountryList();
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_NAME)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Find country by name", description = "Returns a country",
            tags = {"country"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryResponseItem.class))),
            @ApiResponse(responseCode = "400", description = "Invalid name supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "404", description = "Country not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))
    })
    public CountryResponseItem getGetCountryByName(
            @Parameter(name = "The name of country to be retrieved",
                    description = "The name that needs to be fetched.",
                    schema = @Schema(implementation = Long.class), required = true)
            @NotNull @PathVariable(value = "name") String countryName) {
        return countryService.getCountryByName(countryName);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    @Operation(summary = "Add a new country", tags = {"country"})
    @ApiResponses(value = {

            @ApiResponse(responseCode = "201",
                    description = "Returns the created country",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryResponse.class))),
            @ApiResponse(responseCode = "405",
                    description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "409",
                    description = "Invalid input, resource already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "415",
                    description = "Invalid request format, resource request has incorrect format",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetail.class)))
    })
    public CountryResponse addCountry(
            @Parameter(name = "Model to create new item",
                    description = "The model that needs to create new item",
                    schema = @Schema(implementation = CountryRequest.class), required = true)
            @Valid @NotNull @RequestBody CountryRequest countryRequest) {
        return countryService.createCountry(countryRequest);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Operation(summary = "Update an existing country",
            description = "Update an existing country by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successful operation",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Resource not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "405",
                    description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "409",
                    description = "Invalid input, resource already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "415",
                    description = "Invalid request format, resource request has incorrect format",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))
    })
    public CountryResponse updateCountry(
            @Parameter(name = "id", description = "Id of the item to be update. Cannot be empty.",
                    required = true)
            @PathVariable Long id,

            @Parameter(description = "Model to update exist item. Cannot null or empty",
                    schema = @Schema(implementation = CountryRequest.class), required = true)
            @Valid @RequestBody CountryRequest country) {
        return countryService.updateCountry(id, country);
    }

    @DeleteMapping(ControllersApiPaths.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a country", description = "", tags = {"country"})
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successful removed", content = @Content),
            @ApiResponse(responseCode = "400",
                    description = "Invalid unique identifier supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            // stub. will use when auth added
            // stub. will use when auth added
            // @ApiResponse(responseCode = "401", description = "Authentication Failure", content = @Content(schema = @Schema(hidden = true))),

            @ApiResponse(responseCode = "404",
                    description = "Resource not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))
    })
    public void removeCountry(
            @Parameter(name = "The unique identifier", description = "unique identifier to be retrieved",
                    schema = @Schema(implementation = Long.class), required = true)
            @NotNull @PathVariable(value = "id") Long id) {
        countryService.deleteCountry(id);
    }

    @PatchMapping(ControllersApiPaths.UPLOAD_IMAGE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Successful updated"),
            @ApiResponse(responseCode = "400",
                    description = "Invalid unique identifier supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Invalid request format, resource not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))
    })
    public ResponseEntity<String> uploadCountryImage(
            @Parameter(name = "The unique identifier", description = "unique identifier to be retrieved",
                    schema = @Schema(implementation = Long.class), required = true)
            @PathVariable(value = "id") String id,
            @RequestParam("file") MultipartFile file)
            throws ResourceNotFoundException, IOException {
        String name = file.getOriginalFilename();
        long size = file.getSize();

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        if (!"image/jpeg".equals(file.getContentType())) {
            return ResponseEntity.badRequest().body("Only PDF files are allowed");
        }

        String uploadDir = "/uploads";
        Path path = Paths.get(uploadDir, file.getOriginalFilename());

        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        log.info("stored file to:{}",path.getParent());

         return ResponseEntity.ok("Got file " + name + " (" + size + " bytes)");
    }
}
