package ua.se.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.DepartmentEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.DepartmentMapper;
import ua.se.sample.models.request.DepartmentRequest;
import ua.se.sample.models.response.DepartmentResponse;
import ua.se.sample.repository.DepartmentRepository;
import ua.se.sample.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public List<DepartmentResponse> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toDepartmentResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDepartmentResponse)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", id));
    }

    @Override
    public DepartmentResponse create(DepartmentRequest request) {
        DepartmentEntity entity = repository.save(mapper.toEntity(request));
        return mapper.toDepartmentResponse(entity);
    }

    @Override
    public DepartmentResponse update(Long id, DepartmentRequest keywordRequest) {
        DepartmentEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", id));

        entity.setDepartmentName(keywordRequest.getDepartmentName());
        entity = repository.save(entity);
        return mapper.toDepartmentResponse(entity);
    }

    @Override
    public void delete(Long id) {
        DepartmentEntity entity =repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("department", "id", id));

        repository.delete(entity);
    }
}
