package com.example.apiMagneto.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.example.apiMagneto.Validators.ValidDna;

@Getter
@Setter
@AllArgsConstructor
public class DnaRequest {
    @ValidDna
    private String[] dna;
}
