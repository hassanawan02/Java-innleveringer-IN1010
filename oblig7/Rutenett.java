public class Rutenett {
    protected int radnr;
    protected int kolnr;
    protected final int GRID = 12;

    public Rutenett(int radnr, int kolnr){
        this.radnr = radnr;
        this.kolnr = kolnr;
    }
    public int hentRadnr(){
        return radnr;
    }
    public int hentKolnr(){
        return kolnr;
    }
    public void endreRadnr(int radnr){
        this.radnr = radnr;
    }
    public void endreKolnr(int kolnr){
        this.kolnr = kolnr;
    }
    @Override
    public String toString(){
        return "(" + radnr + ", " + kolnr + ")";
    }
}
