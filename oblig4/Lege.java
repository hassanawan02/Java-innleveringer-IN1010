import java.lang.*;
import java.util.*; 

class Lege implements Comparable<Lege>, Godkjenningsfritak{
  private String navn;
  private int kontrollId;
  private IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<Resept>();

  public Lege(String navn, int kontrollId){
    this.navn = navn;
    this.kontrollId = kontrollId;
  }

  public String hentNavn(){
    return navn;
  }

  public int hentKontrollId(){
    return kontrollId;
  }

  public String toString(){
    return "navn: " + navn + ", kortrollid: " + kontrollId;
  }

  public int compareTo(Lege x){
    int result = this.navn.compareTo(x.navn);
    if (result < 0){
      return -1;
    }
    else if (result > 0){
      return 1;
    }
    return 0;
  }

  public void hentUtskrevneResepter(){
    Iterator<Resept> iter = utskrevneResepter.Iterator();
    int teller = 0;

    while (iter.hasNext()==true && utskrevneResepter.stoerrelse() > teller){
      System.out.println(iter.next());
      teller++;
    }
  }

  public Hvite skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift, UlovligType{
    if (legemiddel.type().equals("narkotisk") && kontrollId == 0){
      throw new UlovligUtskrift(this, legemiddel);
    }
    if (legemiddel.type().equals("narkotisk") && kontrollId > 0){
      throw new UlovligType(legemiddel);
    }

    Hvite hvitt = new Hvite(legemiddel, this, pasient, reit);
    pasient.leggTilResept(hvitt);
    utskrevneResepter.leggTil(hvitt);
    return hvitt;
  }

  public Milresept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift, UlovligType{
    if (legemiddel.type().equals("narkotisk") && kontrollId == 0){
      throw new UlovligUtskrift(this, legemiddel);
    }
    if (legemiddel.type().equals("narkotisk") && kontrollId > 0){
      throw new UlovligType(legemiddel);
    }

    Milresept militarresept = new Milresept(legemiddel, this, pasient);
    pasient.leggTilResept(militarresept);
    utskrevneResepter.leggTil(militarresept);
    return militarresept;
  }

  public Presept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift, UlovligType{
    if (legemiddel.type().equals("narkotisk") && kontrollId == 0){
      throw new UlovligUtskrift(this, legemiddel);
    }
    if (legemiddel.type().equals("narkotisk") && kontrollId > 0){
      throw new UlovligType(legemiddel);
    }

    Presept presept = new Presept(legemiddel, this, pasient, reit);
    pasient.leggTilResept(presept);
    utskrevneResepter.leggTil(presept);
    return presept;
  }

  public Blaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift, UlovligType{
    if (legemiddel.type().equals("narkotisk") && kontrollId == 0){
      throw new UlovligUtskrift(this, legemiddel);
    }

    Blaa blaa = new Blaa(legemiddel, this, pasient, reit);
    pasient.leggTilResept(blaa);
    utskrevneResepter.leggTil(blaa);
    return blaa;
  }



}
