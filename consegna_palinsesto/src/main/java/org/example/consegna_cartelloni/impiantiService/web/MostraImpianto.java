package org.example.consegna_cartelloni.impiantiService.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller()
public class MostraImpianto {

    @GetMapping("/impianto/{id}")
    public String mostraImpianto(@PathVariable("id") int idImpianto, Model model) {
        model.addAttribute("idImpianto", idImpianto);
        return "impianto.html"; // Questo deve corrispondere al nome del file HTML (impianto.html)
    }
}
