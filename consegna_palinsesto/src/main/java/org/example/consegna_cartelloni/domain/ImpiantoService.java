package org.example.consegna_cartelloni.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpiantoService {

    @Autowired
    private ImpiantiRepository impiantiRepository;

    public String getNomePalinsestoByImpiantoId(Integer impiantoId) {
        return impiantiRepository.findPalinsestoNomeByImpiantoId(impiantoId);
    }

    public List<Impianto> findAll(){
        return impiantiRepository.findAll();
    }
}
