package com.example.apiMagneto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apiMagneto.Entities.Dna;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DnaRepository extends JpaRepository<Dna, String> {

    Optional<Dna> findByDna(String dnaSequence);

    long countByIsMutant(boolean isMutant);
//
//    @Query("SELECT COUNT(d) FROM Dna d WHERE d.isMutant = true")
//    int countMutantDna();
//
//    @Query("SELECT COUNT(d) FROM Dna d WHERE d.isMutant = false")
//    int countHumanDna();


}
