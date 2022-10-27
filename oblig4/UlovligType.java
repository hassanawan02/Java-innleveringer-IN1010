class UlovligType extends Exception{
  UlovligType (Legemiddel l){
    super("legemiddelet " + l.hentNavn() + ", kan kun skrives ut blaa resepter");
  }
}
