package ua.se.sample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.apierror.ErrorDetail;
import ua.se.sample.models.request.MovieRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MoviePagedResponse;
import ua.se.sample.service.MovieService;


@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.MOVIE_PATH)
@AllArgsConstructor
@Tag(name = "Movie", description = "Movie management APIs")
public class MovieController {

    private final MovieService movieService;

    @GetMapping(name = ControllersApiPaths.PAGED, params = {"page", "size"})
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Paged movies",
            description = "Paged existing movies list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieFullInfoResponse.class)))
    })
    public MoviePagedResponse paginated(
            @Valid
            @Parameter(name = "page", description = "Page number, default is 0")
            @RequestParam(value = "page", defaultValue = "0") int page,

            @Min(1) @Parameter(name = "size", description = "page size, default is 10")
            @RequestParam(value = "size", defaultValue = "1") int size) {

        return movieService.getPagedMovies(page, size);
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Retrieve a movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieFullInfoResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "Resource not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
    })
    public MovieFullInfoResponse getByName(@Valid @NotNull @PathVariable(value = "id") Long id) {
        return movieService.getMovieFullInfo(id);
    }

    @PostMapping(ControllersApiPaths.CREATE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Add a new movie", description = "create new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Returns the created item",
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
                    content = @Content(mediaType = "application/json"
                            , schema = @Schema(implementation = ErrorDetail.class)))
    })
    public MovieFullInfoResponse createMovie(@Parameter(name = "Model to create new item",
            description = "The model that needs to create new item",
            schema = @Schema(implementation = MovieRequest.class), required = true)
                                             @NotNull @Valid @RequestBody MovieRequest movieRequest) {
        return movieService.createNewMovie(movieRequest);
    }

    @PutMapping(ControllersApiPaths.UPDATE)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @Operation(summary = "Update an existing movie",
            description = "Update an existing movie by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CountryResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid model to update"),
            @ApiResponse(responseCode = "404", description = "Resource not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "405",
                    description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "409",
                    description = "Invalid input, resource already exists or data conflicted ",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class))),
            @ApiResponse(responseCode = "415",
                    description = "Invalid request format, resource request has incorrect format",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDetail.class)))
    })
    public MovieFullInfoResponse updateMovie(
            @Parameter(name = "id", description = "Id of the item to be update. Cannot be empty.",
                    required = true)
            @PathVariable Long id,

            @Parameter(description = "Model to update exist item. Cannot null or empty.",
                    schema = @Schema(implementation = MovieRequest.class), required = true)
            @Valid @RequestBody MovieRequest movieRequest) {
        return movieService.updateMovie(id, movieRequest);
    }
}