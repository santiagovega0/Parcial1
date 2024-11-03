package com.example.apiMagneto.Services;

import com.example.apiMagneto.Entities.Dna;
import com.example.apiMagneto.Repositories.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DnaService {

    @Autowired
    private DnaRepository dnaRepository;

    public boolean isMutant(String[] dna) {
        return checkMutant(dna);
    }

    private boolean checkMutant(String[] dna) {
        int count = 0;

        // Verificar desde las esquinas
        count += checkFromCorner(dna, 0, 0, 1, 1); // Esquina superior izquierda
        if (count > 1) return true;

        count += checkFromCorner(dna, 0, dna.length - 1, 1, -1); // Esquina superior derecha
        if (count > 1) return true;

        count += checkFromCorner(dna, dna.length - 1, 0, -1, 1); // Esquina inferior izquierda
        if (count > 1) return true;

        count += checkFromCorner(dna, dna.length - 1, dna.length - 1, -1, -1); // Esquina inferior derecha
        if (count > 1) return true;

        // Verificar los lados interiores
        count += checkInteriorSides(dna);

        return count > 1;
    }

    private int checkFromCorner(String[] dna, int startRow, int startCol, int rowInc, int colInc) {
        int count = 0;
        int n = dna.length;

        for (int i = 0; i < n - 3; i++) {
            // Verificar horizontal
            if (dna[startRow].charAt(startCol + i) == dna[startRow].charAt(startCol + i + 3)) {
                count += countSequences(dna[startRow].substring(startCol + i, startCol + i + 4));
                if (count > 1) return count;
            }

            // Verificar vertical
            if (dna[startRow + i].charAt(startCol) == dna[startRow + i + 3].charAt(startCol)) {
                StringBuilder column = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    column.append(dna[startRow + i + j].charAt(startCol));
                }
                count += countSequences(column.toString());
                if (count > 1) return count;
            }

            // Verificar diagonal
            if (dna[startRow + i].charAt(startCol + i) == dna[startRow + i + 3].charAt(startCol + i + 3)) {
                StringBuilder diagonal = new StringBuilder();
                for (int j = 0; j < 4; j++) {
                    diagonal.append(dna[startRow + i + j].charAt(startCol + i + j));
                }
                count += countSequences(diagonal.toString());
                if (count > 1) return count;
            }
        }

        return count;
    }

    private int checkInteriorSides(String[] dna) {
        int count = 0;
        int n = dna.length;

        // Verificar lados interiores
        for (int i = 1; i < n - 3; i++) {
            // Verificar horizontal
            count += countSequences(dna[0].substring(i, i + 4));
            if (count > 1) return count;

            count += countSequences(dna[n - 1].substring(i, i + 4));
            if (count > 1) return count;

            // Verificar vertical
            StringBuilder column1 = new StringBuilder();
            StringBuilder column2 = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                column1.append(dna[j].charAt(i));
                column2.append(dna[j + n - 4].charAt(i));
            }
            count += countSequences(column1.toString());
            if (count > 1) return count;
            count += countSequences(column2.toString());
            if (count > 1) return count;
        }

        return count;
    }

    private int countSequences(String sequence) {
        int count = 0;
        for (int i = 0; i <= sequence.length() - 4; i++) {
            String sub = sequence.substring(i, i + 4);
            if (sub.equals("AAAA") || sub.equals("TTTT") || sub.equals("CCCC") || sub.equals("GGGG")) {
                count++;
                if (count > 1) return count;  // Detener si hay más de una secuencia
            }
        }
        return count;
    }

    public boolean analyzeDna(String[] dna) {
        String dnaSequence = String.join(",", dna);

        // Verificamos si el ADN ya existe en la base de datos
        Optional<Dna> existingDna = dnaRepository.findByDna(dnaSequence);
        if (existingDna.isPresent()) {
            // Si el ADN ya fue analizado, retornamos el resultado
            return existingDna.get().isMutant();
        }

        // Determinamos si el ADN es mutante y lo guardamos en la base de datos
        boolean isMutant = isMutant(dna);
        Dna dnaEntity = Dna.builder()
                .dna(dnaSequence)
                .isMutant(isMutant)
                .build();
        dnaRepository.save(dnaEntity);

        return isMutant;
    }
}
