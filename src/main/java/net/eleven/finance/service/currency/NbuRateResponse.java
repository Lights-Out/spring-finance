package net.eleven.finance.service.currency;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.eleven.finance.model.Currency;
import net.eleven.finance.model.CurrencyRate;
import net.eleven.finance.model.RateProvider;

import java.util.Date;

/**
 * Created by eleven on 03.12.2018.
 */
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NbuRateResponse {

    @JsonProperty("rate")
    private float rate;

    @JsonProperty("cc")
    private Currency currency;

    @JsonProperty("exchangedate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date forDate;

    public CurrencyRate toCurrencyRate() {
        return new CurrencyRate(currency, rate, forDate, RateProvider.NBU);
    }
}
