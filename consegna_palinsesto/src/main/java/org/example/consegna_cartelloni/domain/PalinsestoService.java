package org.example.consegna_cartelloni.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class PalinsestoService {

    @Autowired
    PalinsestiRepository palinsestiRepository;

    public Map<Integer, String> getListPalinsesti(){
    Iterator<Palinsesto> iterator = palinsestiRepository.findAll().iterator();
    Map<Integer, String> palinsesti = new HashMap<>();
    while(iterator.hasNext()){
        Palinsesto temp = iterator.next();
        palinsesti.put(temp.getId(), temp.getNome());
    }
    return palinsesti;
    }

    public Integer getIdPalinsesto(int idImpianto)
    {
        return palinsestiRepository.getPalinsesto(idImpianto);
    }
}
