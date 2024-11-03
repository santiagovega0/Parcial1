package com.example.deConinckRamiro.services;

import com.example.deConinckRamiro.entities.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.deConinckRamiro.repositories.PersonaRepository;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    private static final int mutanteLength=4;
    //Funciòn que verifica si una Persona es Mutante a traves de su DNA
    public static boolean verificarMutante(@RequestBody String[] dna) {
        int size = dna.length;
        int contadorM = 0;

        // Filas
        contadorM += filas(dna, size);
        if (contadorM > 1) return true;

        // Columnas
        contadorM += columnas(dna, size);
        if (contadorM > 1) return true;

        // Diagonales
        contadorM += diagonales(dna, size);
        return contadorM > 1;
    }
    //Analizamos las filas
    private static int filas(String[] dna, int n) {
        int contadorM = 0;

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 1; j < n; j++) {
                if (dna[i].charAt(j) == dna[i].charAt(j - 1)) {
                    count++;
                    if (count == mutanteLength) {
                        contadorM++;
                        if (contadorM > 1) return contadorM;
                    }
                } else {
                    count = 1;
                }
            }
        }
        return contadorM;
    }
    //Analizamos las columnas
    private static int columnas(String[] dna, int n) {
        int contadorM = 0;
        for (int j = 0; j < n; j++) {
            int count = 1;
            for (int i = 1; i < n; i++) {
                if (dna[i].charAt(j) == dna[i - 1].charAt(j)) {
                    count++;
                    if (count == mutanteLength) {
                        contadorM++;
                        if (contadorM > 1) return contadorM;
                    }
                } else {
                    count = 1;
                }
            }
        }
        return contadorM;
    }
    //Analizamos las diagonales
    private static int diagonales(String[] dna, int n) {
        int contadorM = 0;
        // Analizamos las diagonales de izq a der descendente
        for (int i = 0; i <= n - mutanteLength; i++) {
            for (int j = 0; j <= n - mutanteLength; j++) {
                if (diagonal(dna, i, j, 1, 1, n)) {
                    contadorM++;
                    if (contadorM > 1) return contadorM; // Early exit
                }
            }
        }
        // Analizamos las diagonales de der a izq descendente
        for (int i = 0; i <= n - mutanteLength; i++) {
            for (int j = mutanteLength - 1; j < n; j++) {
                if (diagonal(dna, i, j, 1, -1, n)) {
                    contadorM++;
                    if (contadorM > 1) return contadorM;
                }
            }
        }
        return contadorM;
    }
    //Analizamos la subdiagonal
    private static boolean diagonal(String[] dna, int x, int y, int dx, int dy, int n) {
        char first = dna[x].charAt(y);
        for (int i = 1; i < mutanteLength; i++) {
            if (x + i * dx >= n || y + i * dy >= n || y + i * dy < 0 || dna[x + i * dx].charAt(y + i * dy) != first) {
                return false;
            }
        }
        return true;
    }
    //Verificamos si existe una persona con ese DNA y devolvemos si es mutante o no, si no existe creamos la entidad y la guardamos
    public boolean guardarDna(String[] dna) {
        String dnaSequence = String.join(",", dna);
        Optional<Persona> existingDna = personaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            return existingDna.get().getIsMutant();
        }
        boolean isMutant = verificarMutante(dna);
        Persona dnaEntity = Persona.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        personaRepository.save(dnaEntity);
        return verificarMutante(dna);
    }
}
