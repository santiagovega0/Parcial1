package com.example.deConinckRamiro.repositories;
import com.example.deConinckRamiro.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Optional<Persona> findByDna (String dna);
    long countByIsMutant(boolean isMutant);
}
