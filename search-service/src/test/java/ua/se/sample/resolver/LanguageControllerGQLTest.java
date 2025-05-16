package ua.se.sample.resolver;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.GraphQlResponse;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ua.se.sample.models.response.LanguageResponse;
import ua.se.sample.service.LanguageService;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@GraphQlTest(LanguageControllerGQL.class)
class LanguageControllerGQLTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private LanguageService languageService;

    @Test
    void languageByIdInline() {
        LanguageResponse countryResponseItem = prepareLanguageResponse();
        Mockito.when(languageService.getById(any())).thenReturn(countryResponseItem);

        final String document = """
                {
                  languageById(id: "1") {
                    id
                    name
                  }
                }""";
        GraphQlResponse graphQlResponse = this.graphQlTester
                .document(document)
                .execute()
                .returnResponse();

        this.graphQlTester
                .document(document)
                .execute()
                .path("languageById")
                .matchesJson("""
                        {
                          "id": "1",
                          "name": "Vayshnoria-lang"
                        }""");
    }

    @Test
    void addLanguageInline() {
        final String document = """
                mutation addLanguage {
                    addLanguage(
                      code: "Venkat"
                      name: "Subramaniam"
                    ) {
                  		id
                      name
                      code
                    }
                  }""";
        LanguageResponse langResponse = new LanguageResponse(1L, "Venkat", "Subramaniam");
        Mockito.when(languageService.create(any())).thenReturn(langResponse);


        final LanguageResponse response = this.graphQlTester
                .document(document)
                .execute()
                .path("addLanguage")
                .entity(LanguageResponse.class)
                .get();

        assertThat(response.getCode()).isEqualTo("Subramaniam");
        assertThat(response.getName()).isEqualTo("Venkat");
        assertThat(response.getId()).isNotNull();
    }


    //    languageDetails
    @Test
    void languageDetails() {
        LanguageResponse langResponse = prepareLanguageResponse();
        Mockito.when(languageService.getById(any())).thenReturn(langResponse);

        this.graphQlTester
                .documentName("languageDetails")
                .variable("id", "1")
                .execute()
                .path("languageById")
                .matchesJson("""
                    {
                        "id": "1",
                        "name": "Vayshnoria-lang",
                        "code": "iso"
                    }
                """);
    }

    private LanguageResponse prepareLanguageResponse() {

        return LanguageResponse.builder()
                .id(1L)
                .name("Vayshnoria-lang")
                .code("iso")
                .build();
    }
}