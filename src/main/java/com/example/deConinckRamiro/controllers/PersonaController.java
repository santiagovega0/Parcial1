package com.example.deConinckRamiro.controllers;
import com.example.deConinckRamiro.dto.PersonaReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.deConinckRamiro.services.PersonaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mutant")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @PostMapping
    public ResponseEntity<String> verificarMutante(@RequestBody PersonaReq personaReq) {
        String[] dna = personaReq.getDna();
        boolean isMutant = personaService.guardarDna(dna);
        if (isMutant){
            return ResponseEntity.ok("Mutant detected");
        } else {
            return new ResponseEntity<>("Not a mutant", HttpStatus.FORBIDDEN);
        }
    }
}
