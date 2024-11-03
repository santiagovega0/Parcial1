package mutantes.dto;

public class DnaStats {
    private long countMutantDna; 
    private long countHumanDna;  
    private double ratio;    

    public DnaStats(long countMutantDna, long countHumanDna, double ratio) {
        this.countMutantDna = countMutantDna; 
        this.countHumanDna = countHumanDna;  
        this.ratio = ratio; 
    }

    public long getCountMutantDna() {
        return countMutantDna; 
    }

    public void setCountMutantDna(long countMutantDna) {
        this.countMutantDna = countMutantDna;
    }

    public long getCountHumanDna() {
        return countHumanDna;
    }

    public void setCountHumanDna(long countHumanDna) {
        this.countHumanDna = countHumanDna;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
