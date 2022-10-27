public class Narkotisk extends Legemiddel{
  private int styrke;
  public Narkotisk(String navn, int pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;

  }
  public int hentNarkotiskStyrke(){
    return styrke;
  }
  public String type(){
    return "narkotisk";
  }
  public String toString(){
    return "medisin: " + super.hentNavn() + ", pris: " + super.hentPris() + ", virkestoff; " + super.hentVirkestoff() + ", narkotisk styrke; " + styrke;
}
}
