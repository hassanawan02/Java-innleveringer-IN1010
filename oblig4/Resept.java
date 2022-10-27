abstract class Resept{
  private Legemiddel legemiddel;
  private Lege utskrivendeLege; 
  private Pasient pasient;
  private int reit;
  private int id = 1;
  private static int count = 1;

  public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    this.legemiddel = legemiddel;
    this.utskrivendeLege = utskrivendeLege;
    this.pasient = pasient;
    this.reit = reit;
    id = count++;
  }
  public int hentId(){
    return id;
  }
  public Legemiddel hentLegemiddel(){
    return legemiddel;
  }
  public Lege hentLege(){
    return utskrivendeLege;
  }
  public Pasient hentPasient(){
    return pasient;
  }
  public int hentReit(){
    return reit;
  }
  public boolean bruk(){
    if (reit > 0){
      reit --;
      return true;
    }
    else return false;
  }
  
  public String toString(){
    return id + ": " + legemiddel.hentNavn() + " ( " + reit + " reit)";

  }
  abstract public String Farge();
  abstract public int prisAabetale();

}
