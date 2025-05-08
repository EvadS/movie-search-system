package ua.se.sample.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.se.sample.dao.ProductionCompanyEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.mapper.CompanyMapper;
import ua.se.sample.models.request.CompanyRequest;
import ua.se.sample.models.response.CompanyResponse;
import ua.se.sample.repository.CompanyRepository;
import ua.se.sample.service.CompanyService;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    @Override
    public List<CompanyResponse> list() {
        return repository.findAll()
                .stream()
                .map(mapper::toCompanyResponse)
                .toList();
    }

    @Override
    public CompanyResponse getById(Long id) {
        return  mapper.toCompanyResponse(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductionCompany", "id", id)));

    }

    @Override
    public CompanyResponse create(CompanyRequest request) {
        ProductionCompanyEntity entity = mapper.toEntity(request);
        entity = repository.save(entity);
        return mapper.toCompanyResponse(entity);
    }

    @Override
    public CompanyResponse update(Long id, CompanyRequest companyRequest) {
        ProductionCompanyEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductionCompany", "id", id));

        entity.setCompanyName(companyRequest.getName());
        entity = repository.save(entity);
        return mapper.toCompanyResponse(entity);
    }

    @Override
    public void delete(Long id) {
        ProductionCompanyEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProductionCompany", "id", id));

        repository.delete(entity);
    }
}