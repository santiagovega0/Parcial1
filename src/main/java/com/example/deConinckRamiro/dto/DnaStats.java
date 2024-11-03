package com.example.deConinckRamiro.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DnaStats {
    private long contMutantes; // Contador de ADN mutantes
    private long contHumanos;   // Contador de ADN humanos
    private double promedio;
}
