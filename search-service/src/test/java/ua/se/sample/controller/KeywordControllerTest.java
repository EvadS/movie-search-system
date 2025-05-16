package ua.se.sample.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.request.KeywordRequest;
import ua.se.sample.service.CountryService;
import ua.se.sample.service.KeywordService;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = KeywordController.class)
class KeywordControllerTest {

    @MockBean
    private KeywordService sortingService;

    @Test
    void getList() {
    }

    @Test
    void getById() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }

    private KeywordRequest prepareKeywordRequestRequest(String keyword) {
        return KeywordRequest.builder()
                .name(keyword)
                .build();
    }
}