package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.LanguageRequest;
import ua.se.sample.models.response.LanguageResponse;

import java.util.List;

public interface LanguageService {
    List<LanguageResponse> list();

    LanguageResponse getById(Long id);

    LanguageResponse create(@Valid LanguageRequest request);

    LanguageResponse update(Long id, @Valid LanguageRequest keywordRequest);

    void delete(Long id);
}
