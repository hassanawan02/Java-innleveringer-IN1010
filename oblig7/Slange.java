public class Slange extends Rutenett {
    private boolean erHode;

    public Slange(int radnr, int kolnr, boolean erHode) {
        super(radnr, kolnr);
        this.erHode = erHode; 
    }
    public void settHode() { 
        erHode = true; 
    }

    public void fjernHode() { 
        erHode = false; 
    }

    public boolean erHode() { 
        return erHode; 
    }
}