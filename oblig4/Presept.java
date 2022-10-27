public class Presept extends Hvite{
  public  Presept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }

  public int prisAabetale(){
    if (hentLegemiddel().hentPris() - 108 >= 0){
      return hentLegemiddel().hentPris() - 108;
    }
    else return 0;
  }
}
