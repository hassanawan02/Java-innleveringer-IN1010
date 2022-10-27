abstract class Legemiddel{
  private String navn;
  private int pris; 
  private double virkestoff;
  private int iD = 1;
  private static int count = 1 ;

  public Legemiddel(String navn, int pris, double virkestoff){
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
    iD = count++;
  }
  public int hentId(){
    return iD;
  }
  public String hentNavn(){
    return navn;
  }
  public int hentPris(){
    return pris;
  }
  public double hentVirkestoff(){
    return virkestoff;
  }
  public void settNyPris(int ny){
    pris = ny;
  }
  public String toString(){
    return "navn: " + navn + ", pris: " + pris + ", virkestoff; " + virkestoff;
  }
  public abstract String type();
}
