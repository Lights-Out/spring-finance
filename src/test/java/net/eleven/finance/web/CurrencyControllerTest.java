package net.eleven.finance.web;

import net.eleven.finance.model.Currency;
import net.eleven.finance.repository.CurrencyRepository;
import net.eleven.finance.web.admin.CurrencyController;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by eleven on 27.10.2018.
 */
public class CurrencyControllerTest {
    private static final String USD = "USD";
    private static final Currency TEST_CURRENCY = new Currency(USD);

    CurrencyRepository repository;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        repository = mock(CurrencyRepository.class);
        given(repository.findByCode(USD)).willReturn(TEST_CURRENCY);
        CurrencyController controller = new CurrencyController(repository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testList() throws Exception {
        mockMvc.perform(get("/admin/currencies/list"))
                .andExpect(model().attributeExists("currencies"))
                .andExpect(view().name("admin/currencies/list"));
    }

    @Test
    public void testCurrencyShow() throws Exception {
        mockMvc.perform(get("/admin/currencies/USD"))
                .andExpect(model().attributeExists("currency"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/currencies/show"))
                .andExpect(model().attribute("currency",
                        CoreMatchers.equalTo(TEST_CURRENCY)));
    }

    @Test
    public void testUnknownCurrencyShouldReturnStatusNotFound() throws Exception {
        mockMvc.perform(get("/admin/currencies/HZ"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/admin/currencies/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("currency"))
                .andExpect(view().name("admin/currencies/new"));
    }

    @Test
    public void testProcessCreationFormSuccess() throws Exception {
        Currency currency = new Currency("EUR");
        mockMvc.perform(post("/admin/currencies/new")
            .param("currencyCode", "EUR")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/admin/currencies/EUR"));

        verify(repository, atLeastOnce()).add(currency);
    }

    @Test
    public void testShouldReturnToFormOnFailedValidation() throws Exception {
        mockMvc.perform(post("/admin/currencies/new")
                .param("nonexistingparam", "EUR")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("currency"))
            .andExpect(model().attributeHasFieldErrors("currency", "currencyCode"))
            .andExpect(view().name("admin/currencies/new"));
    }

}