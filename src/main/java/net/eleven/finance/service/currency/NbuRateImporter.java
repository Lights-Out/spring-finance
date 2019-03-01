package net.eleven.finance.service.currency;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.eleven.finance.model.Currency;
import net.eleven.finance.model.CurrencyRate;
import net.eleven.finance.model.RateProvider;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by eleven on 02.12.2018.
 */
@Service
public class NbuRateImporter implements RateImporter {

    private Requester requester;

    private CurrencyService currencyService;

    @Inject
    public NbuRateImporter(@RequesterType(provider = RateProvider.NBU) Requester requester,
                           CurrencyService currencyService) {
        this.requester = requester;
        this.currencyService = currencyService;
    }

    @Override
    public List<CurrencyRate> load() throws IOException {
        String response = requester.makeRequest("");
        ObjectMapper objectMapper = new ObjectMapper();
        List<NbuRateResponse> objects = Arrays.asList(objectMapper.readValue(response, NbuRateResponse[].class));
        return objects
                .stream()
                .map(NbuRateResponse::toCurrencyRate)
                .map(this::toExistingCurrency)
                .filter(rate -> rate.getCurrency() != null)
                .collect(Collectors.toList());
    }

    private CurrencyRate toExistingCurrency(CurrencyRate rate) {
        Currency currency = currencyService.findByCode(rate.getCurrency().getCurrencyCode());
        rate.setCurrency(currency);
        return rate;
    }
}
