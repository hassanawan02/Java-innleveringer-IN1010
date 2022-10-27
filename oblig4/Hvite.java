class Hvite extends Resept{

  public Hvite(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }
  public String Farge(){
    return "hvit";
  }
  public int prisAabetale(){
    return hentLegemiddel().hentPris();
  }

}
