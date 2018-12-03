package net.eleven.finance.service.currency;

import net.eleven.finance.model.CurrencyRate;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by eleven on 02.12.2018.
 */
public class NbuRateImporterTest {

    private static Requester requester;
    private static String response;

    @BeforeClass
    public static void setUp() throws IOException {
        requester = mock(NbuRequester.class);
        response = FileUtils.readFileToString(ResourceUtils.getFile("classpath:currency/nbu_currencies.json"), StandardCharsets.UTF_8);
        given(requester.makeRequest("")).willReturn(response);
    }

    @Test
    public void testImport() throws IOException {
        RateImporter importer = new NbuRateImporter(requester);
        List<CurrencyRate> currencyRates = importer.load();

    }
}