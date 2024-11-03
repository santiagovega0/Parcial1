package com.example.apiMagneto.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "ADN")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Dna extends Base implements Serializable {


    private String dna;
    private boolean isMutant;

}
