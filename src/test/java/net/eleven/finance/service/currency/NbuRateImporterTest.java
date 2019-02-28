package net.eleven.finance.service.currency;

import net.eleven.finance.model.Currency;
import net.eleven.finance.model.CurrencyRate;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by eleven on 02.12.2018.
 */
public class NbuRateImporterTest {

    private static Map<String, Currency> currenciesMap = new HashMap<>();
    private static CurrencyService currencyService;
    private static RateImporter importer;

    @BeforeClass
    public static void setUp() throws IOException {
        prepareCurrencies();

        Requester requester = mock(NbuRequester.class);
        String response = FileUtils.readFileToString(ResourceUtils.getFile("classpath:currency/nbu_currencies.json"),
                StandardCharsets.UTF_8);
        given(requester.makeRequest(anyString())).willReturn(response);
        currencyService = mock(CurrencyService.class);
        given(currencyService
                .findByCode(anyString()))
                .willAnswer(invocationOnMock -> currenciesMap.get(invocationOnMock.getArgument(0))
        );
        importer = new NbuRateImporter(requester, currencyService);
    }

    private static void prepareCurrencies() {
        currenciesMap.put("USD", new Currency("USD"));
        currenciesMap.put("EUR", new Currency("EUR"));
        currenciesMap.put("GBP", new Currency("GBP"));
    }

    @Test
    public void testImport() throws IOException {
        List<CurrencyRate> currencyRates = importer.load();
        assertThat(currencyRates, hasSize(currenciesMap.size()));
    }
}