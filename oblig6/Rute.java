abstract class Rute {
    protected int radnummer, kolonnenummer;
    protected Labyrint labyrint;
    protected Rute[] nabo = new Rute[4]; 

    public Rute(int radnummer, int kolonnenummer, Labyrint labyrint){
        this.radnummer = radnummer;
        this.kolonnenummer = kolonnenummer;
        this.labyrint = labyrint;


    }
    // Lager en metode for å fylle inn naboene
    public void naboer(Rute nord, Rute syd, Rute vest, Rute oest){
        nabo[0] = nord;
        nabo[1] = syd;
        nabo[2] = vest;
        nabo[3] = oest;
    }
    abstract void finn(Rute fra);
        // Disse overskrives i baade hvitrute og sortrute klassene.
    abstract char hentKarakter();
}
