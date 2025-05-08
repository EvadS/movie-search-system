package ua.se.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.GenderEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.GenderMapper;
import ua.se.sample.models.request.GenderRequest;
import ua.se.sample.models.response.GenderResponse;
import ua.se.sample.repository.GenderRepository;
import ua.se.sample.service.GenderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository repository;
    private final GenderMapper mapper;

    @Override
    public List<GenderResponse> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toGenderResponse)
                .toList();
    }

    @Override
    public GenderResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toGenderResponse)
                .orElseThrow(() -> new ResourceNotFoundException("gender", "id", id));
    }

    @Override
    public GenderResponse create(GenderRequest request) {
        GenderEntity entity = repository.save(mapper.toEntity(request));
        return mapper.toGenderResponse(entity);
    }

    @Override
    public GenderResponse update(Long id, GenderRequest keywordRequest) {
        GenderEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("gender", "id", id));

        entity.setGender(keywordRequest.getName());
        entity = repository.save(entity);
        return mapper.toGenderResponse(entity);
    }

    @Override
    public void delete(Long id) {
        GenderEntity entity =repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("gender", "id", id));

        repository.delete(entity);
    }
}
