public class Kontroller{
    private GUI gui;
    private Modell modell;
    private boolean gaarOpp, gaarHoeyre, gaarNed, gaarVenstre;

    private final int DELAY = 500;
    private final int GRID = 12;

    public Kontroller() {
        gui = new GUI(this);
        modell = new Modell(gui, this);
    }

    // starter spill
    public void init() throws InterruptedException {
        modell.startSpill();
        gameLoop();
    }

    // gameloop
    public void gameLoop() throws InterruptedException {
        while (modell.spillKjorer()) {
            Thread.sleep(DELAY);
            oppdater();
            if (!modell.spillKjorer()) break;
            gui.tegnIgjen();
        }
    }

    // tegner brett
    public void startBrett() {
        gui.tegnStart();
    }

    // Bringer frem slange
    public Koe<Slange> hentSlange() { 
        return modell.hentSlange(); 
    }

    // Bringer frem halelengde
    public int hentHalelengde() {
         return modell.hentHalelengde();
     }

    // Bringer frem hode
    public Slange hentHode() {
         return modell.hentHode(); }

    // Bringer frem skatter
    public Skatt[] hentSkatter() {
         return modell.hentSkatter(); }

    // legg til skatt
    public void leggTilSkatt(int pos, Skatt skatt) {
         modell.leggTilSkatt(pos, skatt); }

    // fjern skatt
    public void fjernSkatt(int pos) {
         modell.fjernSkatt(pos); }

    // forlenger slange
    public void forlengSlange(Slange del) {
         modell.forlengSlange(del); }

    // ser etter kollisjon med egen hale
    public void treffHale() {
         modell.treffHale(); }

    // avslutter spill
    public void avsluttSpill() {
         System.exit(0); }

    // oppdaterer elementer i spillet
    public void oppdater() {
        beveg();
        treffHale();
    }


    // beveger slangen
    public void beveg() {
        if (gaarOpp) modell.beveg("opp");
        if (gaarHoeyre) modell.beveg("hoeyre");
        if (gaarNed) modell.beveg("ned");
        if (gaarVenstre) modell.beveg("venstre");
    }

    // oppdaterer bevegelsen til slangen
    public void oppdaterBevegelse(String retning) {


        switch (retning) {
            case "opp":
                if (gaarNed) return;
                gaarOpp = true;
                gaarHoeyre = false;
                gaarNed = false;
                gaarVenstre = false;
                break;
            case "hoeyre":
                if (gaarVenstre) return;
                gaarHoeyre = true;
                gaarOpp = false;
                gaarNed = false;
                gaarVenstre = false;
                
                break;
            case "ned":
                if (gaarOpp) return;
                gaarNed = true;
                gaarOpp = false;
                gaarHoeyre = false;
                gaarVenstre = false;
              
                break;
            case "venstre":
                if (gaarHoeyre) return;
                gaarVenstre = true;
                gaarOpp = false;
                gaarHoeyre = false;
                gaarNed = false;
                break;
        }
    }
}







