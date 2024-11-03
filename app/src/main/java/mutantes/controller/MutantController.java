package mutantes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mutantes.dto.DnaStats;
import mutantes.dto.Request.DnaRequest;
import mutantes.service.MutantService;
import mutantes.repository.DnaRecordRepository;
import mutantes.model.DnaRecord;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mutant")
public class MutantController {

    private final MutantService mutantService;
    private final DnaRecordRepository dnaRecordRepository;

    public MutantController(MutantService mutantService, DnaRecordRepository dnaRecordRepository) {
        this.mutantService = mutantService;
        this.dnaRecordRepository = dnaRecordRepository;
    }

    @PostMapping("/mutants")
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest dnaRequest) {
        boolean isMutant = mutantService.isMutant(dnaRequest.getDna());
        
        String dnaString = String.join(",", dnaRequest.getDna());
        
        if (isMutant) {
            if (!dnaRecordRepository.existsByDna(dnaString)) {
                System.out.println("El ADN no existe, se procederá a guardar: " + dnaString);
                DnaRecord dnaRecord = new DnaRecord();
                dnaRecord.setDna(dnaString);
                dnaRecord.setIsMutant(isMutant);
                dnaRecordRepository.save(dnaRecord);
            } else {
                System.out.println("El ADN ya existe: " + dnaString);
            }
        }

        return isMutant 
            ? ResponseEntity.ok("Mutant detected")
            : new ResponseEntity<>("Not a mutant", HttpStatus.FORBIDDEN);
    }

    @GetMapping("/stats")
    public ResponseEntity<DnaStats> getStats() {
        long countMutantDna = dnaRecordRepository.countByIsMutant(true);
        long countHumanDna = dnaRecordRepository.countByIsMutant(false);
        double ratio = (countHumanDna > 0) ? (double) countMutantDna / countHumanDna : 0;

        DnaStats stats = new DnaStats(countMutantDna, countHumanDna, ratio);

        return ResponseEntity.ok(stats);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("¡La API está funcionando!");
    }
}
