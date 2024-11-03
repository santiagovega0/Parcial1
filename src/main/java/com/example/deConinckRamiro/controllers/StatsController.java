package com.example.deConinckRamiro.controllers;
import com.example.deConinckRamiro.dto.DnaStats;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.deConinckRamiro.services.StatsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    public StatsController (StatsService statsService) {this.statsService = statsService;}
    @GetMapping
    public DnaStats getStats(){return statsService.getStats();}
}
