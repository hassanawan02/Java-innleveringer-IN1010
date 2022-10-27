class Pasient{ 
  private String name, birth;
  private int id = 1;
  private IndeksertListe<Resept> resepts = new IndeksertListe<Resept>();
  static int count = 1; 

  public Pasient(String name, String birth){
    this.name =  name;
    this.birth = birth;
    id = count++;
  }
  public void leggTilResept(Resept x){
    resepts.leggTil(x);
  }
  public IndeksertListe<Resept> pasientens_resepter(){
    return resepts;
  }
  public String hentNavn(){
    return name;
  }
  public String hentFodsel(){
    return birth;
  }
  public int hentId(){
    return id;
  }
  public String toString(){
    return id + ": " + name + " (fnr " + birth + ")";
  }

}
