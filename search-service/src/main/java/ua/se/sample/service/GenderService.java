package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.GenderRequest;
import ua.se.sample.models.response.GenderResponse;

import java.util.List;

public interface GenderService {

    List<GenderResponse> list();

    GenderResponse getById(Long id);

    GenderResponse create(@Valid GenderRequest request);

    GenderResponse update(Long id, @Valid GenderRequest request);

    void delete(Long id);
}
