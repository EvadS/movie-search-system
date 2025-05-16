package ua.se.sample.resolver;

import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ua.se.sample.config.GQLConstants;
import ua.se.sample.models.request.LanguageRequest;
import ua.se.sample.models.response.LanguageResponse;
import ua.se.sample.service.LanguageService;

@Controller
@AllArgsConstructor
public class LanguageControllerGQL {

    private final LanguageService languageService;

    @QueryMapping(GQLConstants.LANGUAGE_BY_ID)
    public LanguageResponse languageById(@Argument Long id) {
        return languageService.getById(id);
    }


    @MutationMapping(GQLConstants.ADD_LANGUAGE)
    public LanguageResponse addLanguage(
            @Argument String iso,
            @Argument String name) {
        LanguageRequest request = new LanguageRequest(iso, name);
        return languageService.create(request);
    }
}
