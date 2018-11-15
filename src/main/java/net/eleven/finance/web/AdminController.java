package net.eleven.finance.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by eleven on 27.10.2018.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/currencies")
    public String currenciesList(Model model) {
        model.addAttribute("title", "Currencies admin");
        return "admin/currencies/list";
    }
}
