package ua.se.sample.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import ua.se.sample.models.response.MovieFullInfoResponse;
import ua.se.sample.models.response.MoviePagedResponse;
import ua.se.sample.service.MovieService;


@Tag(name = "movie", description = "Operation with movie")
@RestController
@RequestMapping(ControllersApiPaths.BASE_PATH + ControllersApiPaths.MOVIE_PATH)
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping(name = ControllersApiPaths.PAGED, params = {"page", "size"})
    @ResponseStatus(value = HttpStatus.OK)
    public MoviePagedResponse findPaginated(
            @Valid
            @NotNull @RequestParam("page") int page,
            @NotNull @Min(1) @RequestParam("size") int size) {
        return movieService.getPagedMovies(page, size);
    }

    @GetMapping(ControllersApiPaths.GET_ITEM_BY_ID)
    @ResponseStatus(value = HttpStatus.OK)

    @Operation(summary = "Retrieve a movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resource found"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })

    public MovieFullInfoResponse getGetCountryByName(@Valid @NotNull @PathVariable(value = "id") Long id) {
        return movieService.getMovieFullInfo(id);
    }
}
