package net.eleven.finance.web.rest;

import lombok.extern.log4j.Log4j2;
import net.eleven.finance.model.Currency;
import net.eleven.finance.model.Product;
import net.eleven.finance.model.ProductDTO;
import net.eleven.finance.repository.CurrencyRepository;
import net.eleven.finance.repository.ProductRepository;
import net.eleven.finance.web.ProductNotFoundException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by eleven on 12.11.2018.
 */
@RestController
@RequestMapping("/rest/products")
@Log4j2
public class ProductController {

    private ProductRepository productRepository;

    public ProductController() {
    }

    @Inject
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/list")
    public Collection<Product> list() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product show(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
            return product;
        } else {
            throw new ProductNotFoundException(id);
        }
    }

//    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("title", "New product");
        model.addAttribute(new Product());
        return "admin/products/new";
    }

    @PostMapping("/new")
    @ResponseBody
    public ProductDTO addNew(@RequestBody @Valid ProductDTO product) {
        HttpHeaders headers = new HttpHeaders();
        return product;
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        PropertyEditorSupport currencyConverter = new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                setValue(new CurrencyConverter().convert(text));
//            }
//        };
////        binder.registerCustomEditor(Currency.class, currencyConverter);
//    }

    static class CurrencyConverter implements Converter<String, Currency> {

        @Inject
        private CurrencyRepository repository;

        @Override
        public Currency convert(String source) {
            return repository.findByCode(source);
        }
    }

}
