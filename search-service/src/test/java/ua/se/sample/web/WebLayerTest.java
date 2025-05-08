package ua.se.sample.web;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;
import ua.se.sample.service.CountryService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *  to enable and configure auto-configuration of MockMvc.
 *  MockMvc to test controllers (all controllers)
 *  End-to-End (E2E) The Loads the complete application context.
 */
@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

    private static final String DEFAULT_ISO = "ISO";
    private static final String DEFAULT_COUNTRY_TEXT = "Country text";
    private final Long DEFAULT_ID = 1L;
    private final String COUNTRY_NAME = "Veyshnoria";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationContext applicationContext;

    @MockitoBean
    private CountryService countryService;


    @BeforeEach
    void printApplicationContext() {
        log.debug("--------------------------------------------------------------------");
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .map(name -> applicationContext.getBean(name).getClass().getName())
                .sorted()
                .forEach(log::debug);
    }

    @Test
    void bookFlightReturnsHttpStatusOk() throws Exception {

        CountryResponseItem countryResponseItem = prepareCountryResponseItem(COUNTRY_NAME);

        when(countryService.getCountryByName(eq(COUNTRY_NAME)))
                .thenReturn(countryResponseItem);

        MvcResult result
                = this.mockMvc
                .perform(get(ControllersApiPaths.BASE_PATH + ControllersApiPaths.GET_ITEM_BY_NAME, COUNTRY_NAME))
                .andExpect(status().isOk())
                .andReturn();
    }

    private CountryRequest prepareCountryRequest(String countryName) {
        return CountryRequest.builder()
                .isoCode("iso")
                .name(countryName)
                .text("text")
                .build();
    }

    //CountryResponseItem
    private CountryResponseItem prepareCountryResponseItem(String countryName) {
        return CountryResponseItem.builder()
                .id(DEFAULT_ID)
                .isoCode(DEFAULT_ISO)
                .name(countryName)
                .build();
    }

    private CountryResponse prepareCountryCountryResponseByRequest(CountryRequest countryRequest) {
        return CountryResponse.builder()
                .id(DEFAULT_ID)
                .isoCode(countryRequest.getIsoCode())
                .name(countryRequest.getName())
                .text(countryRequest.getText())
                .build();
    }
}
