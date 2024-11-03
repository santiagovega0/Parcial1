package com.example.deConinckRamiro.services;

import com.example.deConinckRamiro.dto.DnaStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.deConinckRamiro.repositories.PersonaRepository;

@Service
public class StatsService {

    private final PersonaRepository personaRepository;
    @Autowired
    public StatsService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public DnaStats getStats() {
        long contMutantes = personaRepository.countByIsMutant(true);
        long contHumanos = personaRepository.countByIsMutant(false);
        double promedio = (contHumanos == 0) ? 0.0 : (double) contMutantes / contHumanos;
        return DnaStats.builder()
                .contMutantes(contMutantes)
                .contHumanos(contHumanos)
                .promedio(promedio)
                .build();
    }
}
