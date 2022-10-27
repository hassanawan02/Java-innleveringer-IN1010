public class Subsekvens{
    private final String subsekvens;
    public int antall;

    public Subsekvens(int antall, String subsekvens){
        this.subsekvens = subsekvens;
        this.antall = antall;
    }

    public String hentSubsekvens(){
        return subsekvens;
    }
    public int hentAntall(){
        return this.antall;
    }
    public void endreAntall(int antall){
        this.antall = antall;
    }


    @Override
    public String toString(){
        return subsekvens + "," + antall;
    }
}
