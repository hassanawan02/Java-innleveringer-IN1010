public class SortRute extends Rute{
    public SortRute(int radnummer, int kolonnenummer, Labyrint labyrint){
        super(radnummer, kolonnenummer, labyrint); 
    }
    public char karakter = '#';
    public char hentKarakter(){
        return karakter;
    }
    @Override
    public void finn(Rute fra){
        return;
    }
    @Override
    public String toString(){
        return "Sort rute";
     }
}
