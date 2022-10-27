public class Vanedannende extends Legemiddel{
  private int styrke;

  public Vanedannende(String navn, int pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff); 
    this.styrke = styrke;

  }
  public String type(){
    return "vanedannende";
  }

  public int hentVanedannendeStyrke(){
    return styrke;
  }
  public String toString(){
    return "medisin: " + super.hentNavn() + ", pris: " + super.hentPris() + ", virkestoff; " + super.hentVirkestoff() + ", vanedannende styrke; " + styrke;
}
}
