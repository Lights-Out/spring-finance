package net.eleven.finance.web.admin;

import lombok.extern.log4j.Log4j2;
import net.eleven.finance.model.Product;
import net.eleven.finance.repository.ProductRepository;
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
 * Created by eleven on 12.11.2018.
 */
@Controller
@RequestMapping("/admin/products")
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
    public String list(Model model) {
        model.addAttribute("title", "Products admin");
        Collection<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/products/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
        } else {
            throw new NotFoundException();
        }
        model.addAttribute("title", "Products admin");
        return "admin/products/show";
    }

    @GetMapping("/new")
    public String showNewProductForm(Model model) {
        model.addAttribute("title", "New product");
        model.addAttribute(new Product());
        return "admin/products/new";
    }

    @PostMapping("/new")
    public String addNew(@Valid Product product, BindingResult errors) {
        if (errors.hasErrors()) {
            log.debug("errors found: {}", errors);
            return "admin/products/new";
        }
        log.debug("no errors");
        productRepository.add(product);

        return "redirect:/admin/products/" + product.getName();
    }

}
