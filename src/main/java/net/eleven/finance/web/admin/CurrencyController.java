package net.eleven.finance.web.admin;

import lombok.extern.log4j.Log4j2;
import net.eleven.finance.model.Currency;
import net.eleven.finance.repository.CurrencyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by eleven on 27.10.2018.
 */
@Controller
@RequestMapping("/admin/currencies")
@Log4j2
public class CurrencyController {

    private CurrencyRepository currencyRepository;

    public CurrencyController() {
    }
    @Inject
    public CurrencyController(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @GetMapping("/list")
    public String currenciesList(Model model) {
        model.addAttribute("title", "Currencies admin");
        Collection<Currency> currencies = currencyRepository.findAll();
        model.addAttribute("currencies", currencies);
        return "admin/currencies/list";
    }

    @GetMapping("/{code}")
    public String show(@PathVariable("code") String code, Model model) {
        Currency currency = currencyRepository.findByCode(code);
        if (currency != null) {
            model.addAttribute("currency", currency);
        } else {
            throw new NotFoundException();
        }
        model.addAttribute("title", "Currencies admin");
        return "admin/currencies/show";
    }

    @GetMapping("/new")
    public String showNewCurrencyForm(Model model) {
        model.addAttribute("title", "New currency");
        model.addAttribute(new Currency());
        return "admin/currencies/new";
    }

    @PostMapping("/new")
    public String addNewCurrency(@Valid Currency currency, BindingResult errors) {
        if (errors.hasErrors()) {
            log.debug("errors found: {}", errors);
            return "admin/currencies/new";
        }
        log.debug("no errors");
        currencyRepository.add(currency);

        return "redirect:/admin/currencies/" + currency.getCurrencyCode();
    }
}
