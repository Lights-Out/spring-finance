package net.eleven.finance.service.currency;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @RequesterType(provider = RateProvider.NBU)
    private Requester requester;

    @Inject
    public NbuRateImporter(Requester requester) {
        this.requester = requester;
    }

    @Override
    public List<CurrencyRate> load() throws IOException {
        String response = requester.makeRequest("");
        ObjectMapper objectMapper = new ObjectMapper();
        List<NbuRateResponse> objects = Arrays.asList(objectMapper.readValue(response, NbuRateResponse[].class));
        // TODO: 03.12.2018 converter class parametrized with rate provider (типа new Converter(RateProvider.NBU))
        // TODO: 03.12.2018 фильтровать currency: записывать курсы только для существующих валют
        return objects
                .stream()
                .map(
                        (NbuRateResponse item) -> new CurrencyRate(item.getCurrency(), item.getRate(), item.getForDate(), RateProvider.NBU))
                .collect(Collectors.toList());
    }
}
