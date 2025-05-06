package ua.se.sample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import ua.se.sample.config.ControllersApiPaths;
import ua.se.sample.errors.apierror.ErrorDetail;
import ua.se.sample.errors.exceptions.AlreadyExistsException;
import ua.se.sample.errors.exceptions.DataBaseConstraintException;
import ua.se.sample.errors.exceptions.ResourceNotFoundException;
import ua.se.sample.models.request.CountryRequest;
import ua.se.sample.models.response.CountryResponse;
import ua.se.sample.models.response.CountryResponseItem;
import ua.se.sample.service.CountryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import ua.se.sample.web.ResponseBodyMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Log4j2
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CountryController.class)
class CountryControllerTest {

    private static final String ISO = "iso";
    private static ObjectMapper objectMapper;
    private final Long defaultId = 1L;
    private final String countryName = "Veyshnoria";

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private CountryService countryService;

    @BeforeAll
    static void initAll() {

        // Initialize Jackson mapper to convert response json to object
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    @Test
    @DisplayName("Test get catalogue items")
    void testGetCatalogueItems() {
        try {
            List<CountryResponseItem> countryList = prepareCountryResponse();
            given(countryService.getCountryList()).willReturn(countryList);

            String uriTemplate = ControllersApiPaths.BASE_PATH + ControllersApiPaths.GET_ITEMS;
            MvcResult result
                    = this.mockMvc
                    .perform(get(uriTemplate))
                    .andExpect(status().isOk())
                    .andReturn();

            log.info("response:\n" + result.getResponse().getContentAsString());

            List<CountryResponseItem> myObjects = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CountryResponseItem>>() {
            });
            // Compare size of the list that is returned with the size of list that is expected
            Assertions.assertEquals(
                    myObjects.size(),
                    countryList.size());
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting catalogue items", e);
        }
    }


    @Test
    void testGetCountryItem() {
        String name = "name";
        try {
            CountryResponseItem catalogueItem = prepareCountryResponseItem(1L, name);
            given(countryService.getCountryByName(name)).willReturn(catalogueItem);

            MvcResult result
                    = this.mockMvc
                    .perform(get(ControllersApiPaths.BASE_PATH + ControllersApiPaths.GET_ITEM, name))
                    .andExpect(status().isOk())
                    .andReturn();

            // Compare response object skuNumber to the expected one
            Assertions.assertEquals(
                    objectMapper.readValue(result.getResponse().getContentAsString(), CountryResponseItem.class).getName(),
                    name
            );
        } catch (Exception e) {
            Assertions.fail("Error occurred while getting catalogue items", e);
        }
    }

    @Test
    void testCreateCountry() {
        CountryRequest countryRequest = prepareCountryRequest(countryName);
        CountryResponse countryResponse = prepareCountryCountryResponse(countryRequest);

        given(countryService.createCountry(countryRequest)).willReturn(countryResponse);
        try {
            this.mockMvc
                    .perform(
                            post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(objectMapper.writeValueAsString(countryRequest)))
                    .andExpect(status().isCreated())
                    .andDo(print())
                    .andExpect(jsonPath("$.name").value(countryRequest.getName()))
                    .andExpect(jsonPath("$.text").value(countryRequest.getText()))
                    .andExpect(jsonPath("$.isoCode").value(countryRequest.getIsoCode()));
        } catch (Exception e) {
            Assertions.fail("Error occurred while creating catalogue item", e);
        }
    }


    @Test
    //  @Order(1)
    public void saveEmployeeTest() throws Exception {
        // precondition
        CountryRequest countryRequest = prepareCountryRequest(countryName);
        CountryResponse countryResponse = prepareCountryCountryResponse(countryRequest);

        given(countryService.createCountry(countryRequest)).willReturn(countryResponse);


        // action
        ResultActions response = mockMvc.perform(post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(countryRequest)));

        // verify
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(countryResponse.getName())))
                .andExpect(jsonPath("$.text",
                        is(countryResponse.getText())));
    }

    @Test
    void testUpdateCountryItem() {
        try {
            CountryRequest countryRequest = prepareCountryRequest(countryName);
            CountryResponse countryResponse = prepareCountryCountryResponse(countryRequest);
            //doNothing().when(countryService).updateCountry(defaultId,countryRequest);
            given(countryService.updateCountry(defaultId, countryRequest)).willReturn(countryResponse);

            this.mockMvc
                    .perform(
                            put(ControllersApiPaths.BASE_PATH + ControllersApiPaths.UPDATE, defaultId)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(objectMapper.writeValueAsString(countryRequest))
                    )
                    .andExpect(status().is(HttpStatus.ACCEPTED.value()));
        } catch (Exception e) {
            Assertions.fail("Error occurred while updating catalogue item", e);
        }
    }

    @Test
    @DisplayName("Test update. Resource not found")
    void testUpdateItem() {
        try {
            CountryRequest countryRequest = prepareCountryRequest(countryName);
            when(countryService.updateCountry(any(), any())).thenThrow(new ResourceNotFoundException("country", "id", defaultId));

            this.mockMvc
                    .perform(
                            put(ControllersApiPaths.BASE_PATH + ControllersApiPaths.UPDATE, defaultId)
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                    .content(objectMapper.writeValueAsString(countryRequest))
                    ).andExpect(status().is(HttpStatus.NOT_FOUND.value()));

        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting catalogue item", e);
        }
    }

    @Test
    void testDeleteCountryItemNotFound() {
        try {
            doNothing().when(countryService).deleteCountry(defaultId);

            this.mockMvc
                    .perform(delete(ControllersApiPaths.BASE_PATH + ControllersApiPaths.DELETE, defaultId))
                    .andExpect(status().is(HttpStatus.NO_CONTENT.value()));
        } catch (Exception e) {
            Assertions.fail("Error occurred while deleting catalogue item", e);
        }
    }

    // test arguments: CountryRequest -> CountryResponse
    @Test
    void whenValidInput_thenMapsToBusinessModel() throws Exception {

        CountryRequest countryRequest = prepareCountryRequest(countryName);
        mockMvc.perform(post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isCreated());

        ArgumentCaptor<CountryRequest> userCaptor = ArgumentCaptor.forClass(CountryRequest.class);
        verify(countryService, times(1)).createCountry(userCaptor.capture());
        assertThat(userCaptor.getValue().getName()).isEqualTo(countryName);
        assertThat(userCaptor.getValue().getIsoCode()).isEqualTo(ISO);

    }

    // todo: not found
    // todo: testHandlerNotFound

    // todo: testRuntimeException

//    @Test
//    void testValidationException() {
//
//        try {
//            CountryRequest countryRequest = prepareCountryRequest(countryName);
//            CountryResponse countryResponse = prepareCountryCountryResponse(countryRequest);
//
//            // Set null for few variables to throw validation exception
//            countryRequest.setIsoCode("");
//            countryRequest.setName("");
//
//            given(countryService.createCountry(countryRequest)).willReturn(countryResponse);
//
//            MvcResult result
//                    = this.mockMvc
//                    .perform(
//                            post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
//                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
//                                    .content(objectMapper.writeValueAsString(countryRequest))
//                    )
//                    .andExpect(status().is(HttpStatus.CREATED.value()))
//                    .andReturn();
//        } catch (Exception e) {
//            Assertions.fail("Error occurred while verifying validation exception", e);
//        }
//    }

    @Test
    void whenNullValue_thenReturns400AndErrorResult_withFluentApi() throws Exception {

        CountryRequest countryRequest = prepareCountryRequest(countryName);
        countryRequest.setName("");
        countryRequest.setIsoCode("012345678901234567890");

        mockMvc.perform(post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(ResponseBodyMatchers.responseBody()
                        .containsErrors("isoCode", "size must be between 0 and 10"));
    }


    // check already exists(409) exception handler
    @Test
    void createCountryAlreadyExists() throws Exception {

        CountryRequest countryRequest = prepareCountryRequest(countryName);
        when(countryService.createCountry(any())).thenThrow(new AlreadyExistsException(countryRequest.getName()));

        MockHttpServletResponse response = mockMvc.perform(post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isConflict()) //409
                .andReturn().getResponse();

        ErrorDetail errorResult = objectMapper.readValue(response.getContentAsString(), ErrorDetail.class);

        Assertions.assertEquals("Resource already exists", errorResult.getMessage());
        Assertions.assertEquals("'Veyshnoria' already exists", errorResult.getDetail());
    }

    // handler not found (405)
    @Test
    void createCountryHandlerNotFound() throws Exception {
        CountryRequest countryRequest = prepareCountryRequest(countryName);

        MockHttpServletResponse response = mockMvc.perform(post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE + "fake")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isMethodNotAllowed()) //409
                .andReturn().getResponse();

        ErrorDetail errorResult = objectMapper.readValue(response.getContentAsString(), ErrorDetail.class);

        Assertions.assertEquals("incorrect request", errorResult.getMessage());
        Assertions.assertEquals("Request method 'POST' is not supported", errorResult.getDetail());
    }


    @Test
    void createCountryHttpMediaTypeNotSupported() throws Exception {
        CountryRequest countryRequest = prepareCountryRequest(countryName);

        MockHttpServletResponse response = mockMvc.perform(post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
                        .contentType("")
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isUnsupportedMediaType())
                .andReturn().getResponse();

        ErrorDetail errorResult = objectMapper.readValue(response.getContentAsString(), ErrorDetail.class);

        Assertions.assertEquals("Invalid request", errorResult.getMessage());
        Assertions.assertEquals("Input Request Message cannot be processed", errorResult.getDetail());
    }

    @Test
    void createCountryDataBaseConstraintException() throws Exception {
        CountryRequest countryRequest = prepareCountryRequest(countryName);
        when(countryService.createCountry(any())).thenThrow(new DataBaseConstraintException("country", "name", countryRequest.getName()));

        MockHttpServletResponse response = mockMvc.perform(post(ControllersApiPaths.BASE_PATH + ControllersApiPaths.CREATE)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(countryRequest)))
                .andExpect(status().isConflict())
                .andReturn().getResponse();

        ErrorDetail errorResult = objectMapper.readValue(response.getContentAsString(), ErrorDetail.class);

        Assertions.assertEquals("Constraint exception", errorResult.getMessage());
        Assertions.assertEquals("Resource country by id name used in 'Veyshnoria'", errorResult.getDetail());
    }

    private List<CountryResponseItem> prepareCountryResponse() {
        final List<CountryResponseItem> catalogueItems = new ArrayList<>();
        final Random random = new Random();

        IntStream
                .rangeClosed(1, 10)
                .forEach(i -> {
                    catalogueItems.add(
                            prepareCountryResponseItem((long) i,
                                    "country-" + random.ints(1000, 9999).findFirst().getAsInt())
                    );
                });

        return catalogueItems;
    }


    // utils blocks
    private CountryResponseItem prepareCountryResponseItem(Long i, String s) {

        return CountryResponseItem.builder()
                .id(i)
                .isoCode(ISO)
                .name(s).build();
    }

    private CountryRequest prepareCountryRequest(String countryName) {
        return CountryRequest.builder()
                .isoCode(ISO)
                .name(countryName)
                .text("country text")
                .build();
    }

    private CountryResponse prepareCountryCountryResponse(CountryRequest countryRequest) {
        return CountryResponse.builder()
                .id(defaultId)
                .isoCode(countryRequest.getIsoCode())
                .name(countryRequest.getName())
                .text(countryRequest.getText())
                .build();
    }

    @Test
    public void mapErrorDetail() throws JsonProcessingException {
        String input = "{\n" +
                "  \"message\" : \"Field type mismatch\",\n" +
                "  \"detail\" : \"Constraint validation\",\n" +
                "  \"status\" : 400,\n" +
                "  \"errors\" : [ {\n" +
                "    \"object\" : \"countryRequest\",\n" +
                "    \"field\" : \"isoCode\",\n" +
                "    \"rejectedValue\" : \"012345678901234567890\",\n" +
                "    \"message\" : \"size must be between 0 and 10\"\n" +
                "  } ]\n" +
                "}";

        ErrorDetail actualResponse = objectMapper.readValue(input, ErrorDetail.class);

        Assertions.assertNotNull(actualResponse);
    }

}