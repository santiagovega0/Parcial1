package mutantes.repository;

import mutantes.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {
    boolean existsByDna(String dna);

    long countByIsMutant(boolean isMutant);
}
