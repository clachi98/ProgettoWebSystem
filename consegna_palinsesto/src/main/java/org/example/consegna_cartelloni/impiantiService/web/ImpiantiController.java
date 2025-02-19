package org.example.consegna_cartelloni.impiantiService.web;


import jakarta.persistence.Access;
import org.example.consegna_cartelloni.domain.Impianto;
import org.example.consegna_cartelloni.domain.ImpiantoService;
import org.example.consegna_cartelloni.domain.PalinsestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ImpiantiController {

    @Autowired
    ImpiantoService impiantoService;

    @Autowired
    PalinsestoService palinsestoService;

    @GetMapping("/gestione-impianti")
    public String gestioneImpianti(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Utente autenticato: " + username);


        List<Impianto> impianti = impiantoService.findAll();
        model.addAttribute("impianti", impianti);

        Map<Integer, String> palinsesti = palinsestoService.getListPalinsesti();
        model.addAttribute("palinsesti", palinsesti);

        // Restituisci la vista gestione-impianti.html
        return "gestione-impianti";
    }

}
