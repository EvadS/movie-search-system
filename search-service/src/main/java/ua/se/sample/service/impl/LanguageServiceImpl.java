package ua.se.sample.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.LanguageEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.LanguageMapper;
import ua.se.sample.models.request.LanguageRequest;
import ua.se.sample.models.response.LanguageResponse;
import ua.se.sample.repository.LanguageRepository;
import ua.se.sample.service.LanguageService;

import java.util.List;


@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository repository;
    private final LanguageMapper mapper;

    @Override
    public List<LanguageResponse> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toLanguageResponse)
                .toList();
    }

    @Override
    public LanguageResponse getById(Long id) {
        return mapper.toLanguageResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("language", "id", id)));
    }

    @Override
    public LanguageResponse create(LanguageRequest request) {
        LanguageEntity entity = mapper.toEntity(request);
        entity = repository.save(entity);
        return mapper.toLanguageResponse(entity);
    }

    @Override
    public LanguageResponse update(Long id, LanguageRequest keywordRequest) {
        LanguageEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("language", "id", id));

        entity.setLanguageName(keywordRequest.getName());
        entity.setLanguageCode(keywordRequest.getCode());
        entity = repository.save(entity);
        return mapper.toLanguageResponse(entity);
    }

    @Override
    public void delete(Long id) {
        LanguageEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("language", "id", id));

        repository.delete(entity);
    }
}
