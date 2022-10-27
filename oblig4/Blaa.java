class Blaa extends Resept{

  public Blaa(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
    super(legemiddel, utskrivendeLege, pasient, reit);
  }
  public String Farge(){
    return "Blaa";
  }
  public int prisAabetale(){
    double x = hentLegemiddel().hentPris()*0.25;
    return (int)(x);

  }

}
