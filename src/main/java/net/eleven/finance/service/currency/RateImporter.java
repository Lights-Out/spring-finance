package net.eleven.finance.service.currency;

import net.eleven.finance.model.CurrencyRate;

import java.io.IOException;
import java.util.List;

/**
 * Created by eleven on 02.12.2018.
 */
public interface RateImporter {
    List<CurrencyRate> load() throws IOException;
}
