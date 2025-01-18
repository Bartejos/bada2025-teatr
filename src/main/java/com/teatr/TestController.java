package com.teatr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private DataService dataService;

    // Endpoint do dodania testowego adresu
    @GetMapping("/add-test-adres")
    public String addTestAdres() {
        dataService.addTestAdres(); // Dodaje testowy adres do bazy
        return "redirect:/main"; // Przekierowuje na stronę główną
    }
}
