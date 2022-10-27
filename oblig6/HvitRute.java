
public class HvitRute extends Rute{

    public HvitRute(int radnummer, int kolonnenummer, Labyrint labyrint){
        super(radnummer, kolonnenummer, labyrint);
    }
    public char karakter = '.';
    @Override
    public void finn(Rute fra){
        if (fra == null) {
            nabo[0].finn(this);
            nabo[1].finn(this);
            nabo[2].finn(this);
            nabo[3].finn(this);
        } else {
            if (nabo[0] != null && nabo[0] != fra) {
                nabo[0].finn(this); 
            }

            if (nabo[1] != null && nabo[1] != fra) {
                nabo[1].finn(this);
            }

            if (nabo[2] != null && nabo[2] != fra) {
                nabo[2].finn(this);
            }

            if (nabo[3] != null && nabo[3] != fra) {
                nabo[3].finn(this);
            }
        }

        return;
    }
    @Override
    public String toString(){
       return "Hvit rute";
    }
    public char hentKarakter(){
        return karakter;
    }
}
