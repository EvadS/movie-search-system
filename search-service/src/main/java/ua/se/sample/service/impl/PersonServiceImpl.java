package ua.se.sample.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.LanguageEntity;
import ua.se.sample.dao.PersonEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.PersonMapper;
import ua.se.sample.models.request.PersonRequest;
import ua.se.sample.models.response.PersonResponse;
import ua.se.sample.repository.PersonRepository;
import ua.se.sample.service.PersonService;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;
    private final PersonMapper mapper;

    @Override
    public List<PersonResponse> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toPersonResponse)
                .toList();
    }

    @Override
    public PersonResponse getById(Long id) {
        return  mapper.toPersonResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id)));

}

    @Override
    public PersonResponse create(PersonRequest request) {
        PersonEntity entity = mapper.toEntity(request);
        entity = repository.save(entity);
        return mapper.toPersonResponse(entity);
    }

    @Override
    public PersonResponse update(Long id, PersonRequest keywordRequest) {
        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        entity.setPersonName(keywordRequest.getName());
        entity = repository.save(entity);
        return mapper.toPersonResponse(entity);
    }

    @Override
    public void delete(Long id) {
        PersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("person", "id", id));

        repository.delete(entity);
    }
}
