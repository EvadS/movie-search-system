package ua.se.sample.resolver;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ua.se.sample.dao.LanguageEntity;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.LanguageRequest;
import ua.se.sample.repository.LanguageRepository;

@Controller
public class LanguageControllerGQL {
    LanguageRepository repository;

    LanguageControllerGQL(LanguageRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public Iterable<LanguageEntity> languages() {
        return repository.findAll();
    }

    @QueryMapping
    public LanguageEntity language(@Argument Long id) {
        return repository.findById(id).orElseThrow();
    }

    @MutationMapping
    public LanguageEntity newLanguage(@Argument LanguageRequest languageRequest) {
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguageName(languageEntity.getLanguageName());
        languageEntity.setLanguageCode(languageEntity.getLanguageCode());

        return repository.save(languageEntity);
    }

    @MutationMapping
    public LanguageEntity update(@Argument Long id,  @Argument LanguageRequest languageRequest) {
        LanguageEntity languageEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("land", "id", id));

        languageEntity.setLanguageName(languageRequest.getName());
        languageEntity.setLanguageCode(languageRequest.getCode());

        languageEntity = repository.save(languageEntity);

        return  languageEntity;
    }

}
