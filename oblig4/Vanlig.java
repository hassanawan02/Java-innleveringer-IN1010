class Vanlig extends Legemiddel{
  public Vanlig(String navn, int pris, double virkestoff){
    super(navn, pris, virkestoff);

  }
  public String type(){
    return "vanlig";
  }
}
