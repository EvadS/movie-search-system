package ua.se.sample.resolver;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.GraphQlResponse;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ua.se.sample.config.GQLConstants;
import ua.se.sample.config.GraphQLConfig;
import ua.se.sample.models.response.LanguageResponse;
import ua.se.sample.service.LanguageService;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@Import(GraphQLConfig.class)
//@Import(LanguageService.class)
@GraphQlTest(LanguageControllerGQL.class)
class LanguageControllerGQLTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @MockitoBean
    private LanguageService languageService;

    @Test
    void languageByIdInline() {
        LanguageResponse countryResponseItem = prepareLanguageResponse();
        Mockito.when(languageService.list()).thenReturn(List.of(countryResponseItem));

        String query = "{ languages { id name code } }";
        List<LanguageResponse> allLanguages = graphQlTester.document(query)
                .execute()
                .path("data.languages[*]")
                .entityList(LanguageResponse.class)
                .get();

        Assertions.assertTrue(allLanguages.size() > 0);
        Assertions.assertNotNull(allLanguages.get(0).getId());
        Assertions.assertNotNull(allLanguages.get(0).getName());
        Assertions.assertNotNull(allLanguages.get(0).getCode());
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
                .path(GQLConstants.ADD_LANGUAGE)
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

        LanguageResponse response = this.graphQlTester
                .documentName("languageDetails")
                .variable("id", 1L)
                .execute()
                .path(GQLConstants.LANGUAGE_BY_ID)
                .entity(LanguageResponse.class)
                .get();

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo(langResponse.getName());
        assertThat(response.getCode()).isEqualTo(langResponse.getCode());
     }

    private LanguageResponse prepareLanguageResponse() {

        return LanguageResponse.builder()
                .id(1L)
                .name("Vayshnoria-lang")
                .code("iso")
                .build();
    }

    @Test
    void findAll() {

        List<LanguageResponse> langResponse = Collections.singletonList(prepareLanguageResponse());
        Mockito.when(languageService.list()).thenReturn(langResponse);
//
//       this.graphQlTester
//                .documentName("books.graphql")
//                .execute()
//                .path("languages");

//        List<LanguageResponse> departments = this.graphQlTester.document(query)
//                .execute()
//                .path("data.languages[*]")
//                .entityList(LanguageResponse.class)
//                .get();

        int a =0;

      //  Assertions.assertTrue(departments.size() > 0);
      //  Assertions.assertNotNull(departments.get(0).getId());
      //  Assertions.assertNotNull(departments.get(0).getName());
    }
}