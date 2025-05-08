package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.PersonRequest;
import ua.se.sample.models.response.PersonResponse;


import java.util.List;

public interface PersonService {
    List<PersonResponse> list();

    PersonResponse getById(Long id);

    PersonResponse create(@Valid PersonRequest PersonRequest);

    PersonResponse update(Long id, @Valid PersonRequest keywordRequest);

    void delete(Long id);
}
