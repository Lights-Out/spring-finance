package net.eleven.finance.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.eleven.finance.model.Currency;
import net.eleven.finance.model.Product;
import net.eleven.finance.repository.ProductRepository;
import net.eleven.finance.web.ProductNotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by eleven on 19.11.2018.
 */
//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = {RootConfig.class})
@ActiveProfiles("Test")
public class ProductControllerTest {

    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DataSource dataSource;

    @Ignore
    public void testFFF() {
        System.out.println("DATA SOURCE = " + dataSource);
    }

    private MockMvc mockMvc;

    @Mock
    private ProductRepository repository;

    @Before
    public void setUp() {
        repository = mock(ProductRepository.class);
        ProductController controller = new ProductController(repository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void testList() throws Exception {

        Product first = new Product("first", 322.0f, new Currency("USD"));
        Product second = new Product("second", 422.0f, new Currency("EUR"));
        List<Product> list = asList(first, second);
        given(repository.findAll()).willReturn(list);

        mockMvc.perform(get("/rest/products/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("first")))
                .andExpect(jsonPath("$[0].price", closeTo(322.0f, 0.01)))
                .andExpect(jsonPath("$[0].currency.currencyCode", is("USD")))
                .andExpect(jsonPath("$[1].name", is("second")))
                .andExpect(jsonPath("$[1].price", closeTo(422.0f, 0.01)))
                .andExpect(jsonPath("$[1].currency.currencyCode", is("EUR")));

        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void findByNonExistentIdShouldReturnHttpStatusCode404() throws Exception {
        when(repository.findById(1L)).thenThrow(new ProductNotFoundException(1L));
        mockMvc.perform(get("/rest/products/{id}", 1L))
                .andExpect(status().isNotFound());
        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void findByIdShouldReturnProduct() throws Exception {
        Product product = new Product("first", 322.0f, new Currency("USD"));
        given(repository.findById(1L)).willReturn(product);

        mockMvc.perform(get("/rest/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
                .andExpect(jsonPath("$.name", is("first")))
                .andExpect(jsonPath("$.price", closeTo(322.0f, 0.01)))
                .andExpect(jsonPath("$.currency.currencyCode", is("USD")));

        verify(repository, times(1)).findById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void negativePriceShouldReturnValidationError() throws Exception {
        Product product = new Product("first", -1.0f, new Currency("USD"));

        mockMvc.perform(post("/rest/products/new")
                .contentType(APPLICATION_JSON_CHARSET_UTF_8)
                .content(new ObjectMapper().writeValueAsBytes(product))
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8));
    }
}