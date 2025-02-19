package org.example.consegna_cartelloni.impiantiService.web;

import org.example.consegna_cartelloni.domain.ImpiantoService;
import org.example.consegna_cartelloni.domain.PalinsestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")//non devo restituire una vista, quindi uso restController che include annotazione @ResponseBody di default
@CrossOrigin(origins = "http://localhost:8080")
public class getPalinsestoController {

    @Autowired
    private ImpiantoService impiantoService;

    @Autowired
    private PalinsestoService palinsestoService;


    @GetMapping("/getPalinsesto")
    public ResponseEntity<Map<String, String>> getPalinsesto(@RequestParam int id_impianto) {

        try {
            String nomePalinsesto = impiantoService.getNomePalinsestoByImpiantoId(id_impianto);
            nomePalinsesto += ".xml";
            Map<String, String> response = new HashMap<>();

            response.put("xmlFileName", nomePalinsesto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @GetMapping("/getIdPalinsesto")
    public ResponseEntity<Map<String, String>> getIdPalinsesti(@RequestParam int id_impianto) {
        try {
            Integer idPalinsesto = palinsestoService.getIdPalinsesto(id_impianto);
            Map<String, String> response = new HashMap<>();
            response.put("id_palinsesto", idPalinsesto.toString());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @GetMapping("/getListPalinsesti")
    public ResponseEntity<Map<Integer, String>> getListPalinsesti() {
        return ResponseEntity.ok(palinsestoService.getListPalinsesti());
    }

}
