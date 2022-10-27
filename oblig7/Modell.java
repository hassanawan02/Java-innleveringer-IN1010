public class Modell{
    private GUI gui;
    private Kontroller kontroller;
    private boolean start;
    private Koe<Slange> slange = new Koe<>();
    private Skatt[] skatter = new Skatt[10];


    public Modell(GUI gui, Kontroller kontroller){
        this.gui = gui;
        this.kontroller = kontroller;
    }
    public void startSpill(){
        start = true;
        kontroller.startBrett();

    }
    public Koe<Slange> hentSlange(){
        return slange;
    }
    public int hentHalelengde(){
        return slange.storrelse();
    }
    public Slange hentHode(){
        for(Slange part : slange){
            if(part.erHode()){
                return part;
            }
        }
        return null;
    }
    public Skatt[] hentSkatter(){
        return skatter;
    }
    public void leggTilSkatt(int pos, Skatt skatt){
        skatter[pos] = skatt;
    }
    public void fjernSkatt(int pos){
        skatter[pos] = null;
    }
    public void forlengSlange(Slange part){
        slange.leggTil(part);
    }
    public void forkortSlange(){
        slange.fjern();
    }
    public void avsluttSpill(){
        start = false;
    }
    public boolean spillKjorer(){
        return start;
    }
    public void treffHale(){
        if(slange.storrelse() > 1){
            for(Slange part : slange){
                if(hentHode().hentRadnr() == part.hentRadnr() && hentHode().hentKolnr() == part.hentKolnr() && !part.erHode()){
                    avsluttSpill();
                }
            }
        }
    }
    public void leggTilSkatt(int pos){
        int skattRad = 0;
        int skattKol = 0;
        boolean ingenTreff = false;

        while(true){
            for(Slange part : slange){
                for(Skatt skatt : skatter){
                    if(skatt != null){
                        skattRad = Skatt.trekk(0,11);
                        skattKol = Skatt.trekk(0, 11);
                        if(skattRad != part.hentRadnr() && skattRad != skatt.hentRadnr() && skattKol != part.hentKolnr() && skattKol != skatt.hentKolnr()){
                            ingenTreff = true;
                        }else{
                            ingenTreff = false;
                        }
                    }
                }
            }
            if(ingenTreff){
                break;
            }
            
        }
    
        skatter[pos] = new Skatt(skattRad, skattKol);
    }

    public boolean kollisjon(){
        for(int i = 0; i < skatter.length; i++){
            for(Slange part : slange){
                if(skatter[i] != null){
                    if(skatter[i].hentRadnr() == part.hentRadnr() && skatter[i].hentKolnr() == part.hentKolnr()){
                        fjernSkatt(i);
                        leggTilSkatt(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void beveg(String retning){
        Slange ny;

        switch(retning){
            case "opp":
            if(hentHode().hentRadnr() - 1 < 0){
                avsluttSpill();
            }
            if(slange.storrelse() == 1){
                ny = new Slange(hentHode().hentRadnr() - 1, hentHode().hentKolnr(), true);
                if (!kollisjon()) slange.fjern();
                for (Slange part : slange){
                    part.fjernHode();
                }
                slange.leggTil(ny);
                return;
            }
            ny = new Slange(hentHode().hentRadnr() - 1, hentHode().hentKolnr(), true);
            for (Slange part: slange) part.fjernHode();
            if (!kollisjon()) slange.fjern();
            slange.leggTil(ny); 
            break;

        case "hoeyre":
            if (hentHode().hentKolnr() + 1 > 11) avsluttSpill();
            
            if (slange.storrelse() == 1) {
                ny = new Slange(hentHode().hentRadnr(), hentHode().hentKolnr() + 1, true);
                if (!kollisjon()) slange.fjern();
                for (Slange part: slange) part.fjernHode();
                slange.leggTil(ny);
                return;
            }

            ny = new Slange(hentHode().hentRadnr(), hentHode().hentKolnr() + 1, true);
            for (Slange part: slange) part.fjernHode();
            if (!kollisjon()) slange.fjern();
            slange.leggTil(ny);
            break;

        case "ned":
            if (hentHode().hentRadnr() + 1 > 11) avsluttSpill();

            if (slange.storrelse() == 1) {
                ny = new Slange(hentHode().hentRadnr() + 1, hentHode().hentKolnr(), true);
                if (!kollisjon()) slange.fjern();
                for (Slange part: slange) part.fjernHode();
                slange.leggTil(ny);
                return;
            }

            ny = new Slange(hentHode().hentRadnr() + 1, hentHode().hentKolnr(), true); 
            for (Slange part: slange) part.fjernHode();
            if (!kollisjon()) slange.fjern();
            slange.leggTil(ny);
            break;
            
        case "venstre":
            if (hentHode().hentKolnr() - 1 < 0) avsluttSpill();

            if (slange.storrelse() == 1) {
                ny = new Slange(hentHode().hentRadnr(), hentHode().hentKolnr() - 1, true);
                if (!kollisjon()) slange.fjern();
                for (Slange part: slange) part.fjernHode();
                slange.leggTil(ny);
                return;
            }

            ny = new Slange(hentHode().hentRadnr(), hentHode().hentKolnr() - 1, true);
            for (Slange part: slange) part.fjernHode();
            if (!kollisjon()) slange.fjern();
            slange.leggTil(ny);
            break;
        }
    }
}