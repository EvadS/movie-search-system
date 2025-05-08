package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.GenreRequest;
import ua.se.sample.models.response.GenreResponse;

import java.util.List;

public interface GenreService {
    List<GenreResponse> list();

    GenreResponse getById(Long id);

    GenreResponse create(@Valid GenreRequest request);

    GenreResponse update(Long id, @Valid GenreRequest request);

    void delete(Long id);
}
