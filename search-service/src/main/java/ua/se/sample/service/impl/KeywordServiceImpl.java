package ua.se.sample.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ua.se.sample.dao.KeywordEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.KeywordMapper;
import ua.se.sample.models.request.KeywordRequest;
import ua.se.sample.models.response.KeywordResponse;
import ua.se.sample.repository.KeywordRepository;
import ua.se.sample.service.KeywordService;

import java.util.List;

@Service
@AllArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository repository;
    private final KeywordMapper mapper;

    @Override
    public List<KeywordResponse> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toKeywordResponse)
                .toList();
     }

    @Override
    public KeywordResponse getById(Long id) {
        KeywordEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("keyword", "id", id));

        return  mapper.toKeywordResponse(entity);
    }

    @Override
    public KeywordResponse create(KeywordRequest keywordRequest) {
        KeywordEntity entity = mapper.toEntity(keywordRequest);
        entity = repository.save(entity);
        return mapper.toKeywordResponse(entity);
    }

    @Override
    public KeywordResponse update(Long id, KeywordRequest keywordRequest) {
        KeywordEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("keyword", "id", id));

        entity.setKeywordName(keywordRequest.getName());
        entity = repository.save(entity);
        return mapper.toKeywordResponse(entity);
    }

    @Override
    public void delete(Long id) {
        KeywordEntity entity =repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("keyword", "id", id));

        repository.delete(entity);
    }
}
