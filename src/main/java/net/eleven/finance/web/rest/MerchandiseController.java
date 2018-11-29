package net.eleven.finance.web.rest;

import lombok.extern.log4j.Log4j2;
import net.eleven.finance.model.Merchandise;
import net.eleven.finance.repository.MerchandiseRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by eleven on 26.11.2018.
 */
@Log4j2
@RestController
@RequestMapping("/rest/merchandises")
public class MerchandiseController {
    private MerchandiseRepository repository;

    public MerchandiseController() {
    }

    @Inject
    public MerchandiseController(MerchandiseRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/new")
    public Merchandise addNew(@RequestBody @Valid Merchandise merchandise) {
        log.debug("adding new merchandise: {}", merchandise);
        repository.add(merchandise);
        return merchandise;
    }
}
