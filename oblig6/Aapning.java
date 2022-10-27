public class Aapning extends HvitRute{
    public Aapning(int radnummer, int kolonnenummer, Labyrint labyrint){
        super(radnummer, kolonnenummer, labyrint);
    } 
    @Override
    public void finn(Rute fra){
        System.out.println("(" + radnummer + ", " + kolonnenummer + ")");
        return;
    }
}
