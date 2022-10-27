public class Skatt extends Rutenett {

    public Skatt(int radnr, int kolnr) {
        super(radnr, kolnr);
    }
    // 
    static int trekk(int min, int max) { 
        return (int)(Math.random()*(max - min + 1)) + min; 
    } 
}