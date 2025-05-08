package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.KeywordRequest;
import ua.se.sample.models.response.KeywordResponse;

import java.util.List;

public interface KeywordService {

    List<KeywordResponse> list();

    KeywordResponse getById(Long id);

    KeywordResponse create(@Valid KeywordRequest keywordRequest);

    KeywordResponse update(Long id, @Valid KeywordRequest keywordRequest);

    void delete(Long id);
}
