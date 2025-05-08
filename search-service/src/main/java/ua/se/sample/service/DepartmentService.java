package ua.se.sample.service;

import jakarta.validation.Valid;
import ua.se.sample.models.request.DepartmentRequest;
import ua.se.sample.models.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> list();

    DepartmentResponse getById(Long id);

    DepartmentResponse create(@Valid DepartmentRequest request);

    DepartmentResponse update(Long id, @Valid DepartmentRequest request);

    void delete(Long id);
}
