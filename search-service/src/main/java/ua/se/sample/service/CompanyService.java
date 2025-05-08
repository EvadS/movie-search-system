package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.CompanyRequest;
import ua.se.sample.models.response.CompanyResponse;

import java.util.List;

public interface CompanyService {
    List<CompanyResponse> list();

    CompanyResponse getById(Long id);

    CompanyResponse create(@Valid CompanyRequest request);

    CompanyResponse update(Long id, @Valid CompanyRequest companyRequest);

    void delete(Long id);
}
