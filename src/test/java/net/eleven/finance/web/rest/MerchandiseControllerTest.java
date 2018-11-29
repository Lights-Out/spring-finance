package net.eleven.finance.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.eleven.finance.configuration.RootConfig;
import net.eleven.finance.model.Currency;
import net.eleven.finance.model.Merchandise;
import net.eleven.finance.model.Product;
import net.eleven.finance.repository.MerchandiseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by eleven on 26.11.2018.
 */
@ActiveProfiles("Test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class MerchandiseControllerTest {
    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";

    private MockMvc mockMvc;

    @Inject
    private MerchandiseRepository repository;

    @Before
    public void setUp() {
//        repository = mock(MerchandiseRepository.class);
        MerchandiseController controller = new MerchandiseController(repository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    public void testNewMerchandiseShouldRequireExistingProduct() throws Exception {
        Product product = new Product("first", 11.0f, new Currency("USD"));
        Merchandise merchandise = new Merchandise(product, 5);

        mockMvc.perform(post("/rest/merchandises/new")
        .contentType(APPLICATION_JSON_CHARSET_UTF_8)
        .content(new ObjectMapper().writeValueAsBytes(merchandise)))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8));



    }
}
