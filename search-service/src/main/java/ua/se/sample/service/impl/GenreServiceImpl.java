package ua.se.sample.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.GenreEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.GenreMapper;
import ua.se.sample.models.request.GenreRequest;
import ua.se.sample.models.response.GenreResponse;
import ua.se.sample.repository.GenreRepository;
import ua.se.sample.service.GenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;
    private final GenreMapper mapper;

    @Override
    public List<GenreResponse> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toGenreResponse)
                .toList();
    }

    @Override
    public GenreResponse getById(Long id) {
        return repository.findById(id)
                .map(mapper::toGenreResponse)
                .orElseThrow(() -> new ResourceNotFoundException("genre", "id", id));

    }

    @Override
    public GenreResponse create(GenreRequest request) {
        GenreEntity entity = repository.save(mapper.toEntity(request));
        return mapper.toGenreResponse(entity);
    }

    @Override
    public GenreResponse update(Long id, GenreRequest request) {
        GenreEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("genre", "id", id));

        entity.setGenreName(request.getName());
        entity = repository.save(entity);
        return mapper.toGenreResponse(entity);
    }

    @Override
    public void delete(Long id) {
        GenreEntity entity =repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("genre", "id", id));

        repository.delete(entity);
    }
}
