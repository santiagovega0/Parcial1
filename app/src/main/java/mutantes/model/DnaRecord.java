package mutantes.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "dna_records")
@Data
@NoArgsConstructor
public class DnaRecord {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String dna;

    @Column(nullable = false)
    private Boolean isMutant;
}
