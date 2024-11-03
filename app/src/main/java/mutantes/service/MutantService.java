package mutantes.service;

import org.springframework.stereotype.Service; 

@Service 
public class MutantService {
   
    
    public boolean isMutant(String[] dna) {
        
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna[i].length(); j++) {
                if (j + 3 < dna[i].length() && dna[i].charAt(j) == dna[i].charAt(j + 3)) {
                    if (dna[i].charAt(j) == dna[i].charAt(j + 1) && dna[i].charAt(j) == dna[i].charAt(j + 2)) {
                        return true;
                    }
                }
                if (i + 3 < dna.length && dna[i].charAt(j) == dna[i + 3].charAt(j)) {
                    if (dna[i].charAt(j) == dna[i + 1].charAt(j) && dna[i].charAt(j) == dna[i + 2].charAt(j)) {
                        return true; 
                    }
                }
                if (i + 3 < dna.length && j + 3 < dna[i].length() && dna[i].charAt(j) == dna[i + 3].charAt(j + 3)) {
                    if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1) && dna[i].charAt(j) == dna[i + 2].charAt(j + 2)) {
                        return true;
                    }
                }
                if (i + 3 < dna.length && j - 3 >= 0 && dna[i].charAt(j) == dna[i + 3].charAt(j - 3)) {
                    if (dna[i].charAt(j) == dna[i + 1].charAt(j - 1) && dna[i].charAt(j) == dna[i + 2].charAt(j - 2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
