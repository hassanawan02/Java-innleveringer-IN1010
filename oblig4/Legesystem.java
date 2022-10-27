import java.lang.*;
import java.util.*; 
import java.io.*;


class Legesystem{
  private IndeksertListe<Pasient> pasienter = new IndeksertListe<Pasient>();
  private IndeksertListe<Legemiddel> legemiddeler = new IndeksertListe<Legemiddel>();
  private IndeksertListe<Lege> leger = new IndeksertListe<Lege>();
  private IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();



  public void LefFraFil(String filename) throws FileNotFoundException, UlovligType, UlovligUtskrift{
    File fil = new File (filename);
    Scanner scan = new Scanner(fil);
    IndeksertListe<String> liste = new IndeksertListe<String>();

    int teller = 0;
    while (scan.hasNext()){
      String s = scan.nextLine();
      liste.leggTil(teller,s);
      teller++;

    }

    int ant_linjer = liste.stoerrelse();
    teller = 0;
    for (int i = 0+1; i<ant_linjer/4; i++){
      String biter[] = liste.hent(i).split(",");
      Pasient p = new Pasient(biter[0], biter[1]);
      pasienter.leggTil(teller,p);
      teller++;
    }

    for (int i = ant_linjer/4+1; i<ant_linjer/4*2; i++){
      String biter[] = liste.hent(i).split(",");
      String name = biter[0];
      String type = biter[1];
      int pris = Integer.parseInt(biter[2]);
      double virkestoff = Double.parseDouble(biter[3]);

      if (type.equals("narkotisk")){
        int styrke = Integer.parseInt(biter[4]);
        Narkotisk l = new Narkotisk(name, pris, virkestoff, styrke);
        legemiddeler.leggTil(teller,l);
        teller++;
      }
      else if (type.equals("vanedannende")){
        int styrke = Integer.parseInt(biter[4]);
        Vanedannende l = new Vanedannende(name, pris, virkestoff, styrke);
        legemiddeler.leggTil(teller,l);
        teller++;
      } else if (type.equals("vanlig")){
        Vanlig l = new Vanlig(name, pris, virkestoff);
        legemiddeler.leggTil(teller,l);
        teller++;
      }

    }

    for (int i = ant_linjer/4*2+1; i<ant_linjer/4*3; i++){
      String biter[] = liste.hent(i).split(",");
      int kontrollID = Integer.parseInt(biter[1]);
      Lege d = new Lege(biter[0], kontrollID);
      leger.leggTil(teller,d);
      teller++;
    }

    for (int i = ant_linjer/4*3+1; i < ant_linjer; i++){
      Lege doc = null;
      Legemiddel medisin = null;
      Pasient pasIent = null;

      String biter[] = liste.hent(i).split(",");
      int legemiddelnr = Integer.parseInt(biter[0]);
      int pasinetnr = Integer.parseInt(biter[2]);

      for (int I = 0; I < pasienter.stoerrelse(); I++){
        if (pasienter.hent(I).hentId()==pasinetnr){
          pasIent = pasienter.hent(I);
        }
      }
      for (int J = 0; J < legemiddeler.stoerrelse(); J ++){
        if (legemiddeler.hent(J).hentId()==legemiddelnr){
          medisin = legemiddeler.hent(J);
        }
      }
      for (int k = 0; k < leger.stoerrelse(); k++){
        if (leger.hent(k).hentNavn().equals(biter[1])){
          doc = leger.hent(k);
        }
      }
      if (biter[3].equals("hvit")){
        Resept r = doc.skrivHvitResept(medisin, pasIent, Integer.parseInt(biter[4]));
        resepter.leggTil(teller,r);
        teller++;
      }
      else if (biter[3].equals("blaa")){
        Resept r = doc.skrivBlaaResept(medisin, pasIent, Integer.parseInt(biter[4]));
        resepter.leggTil(teller,r);
        teller++;
      }
      else if (biter[3].equals("militaer")){
        Resept r = doc.skrivMilResept(medisin, pasIent);
        resepter.leggTil(teller,r);
        teller++;
      }
      else if (biter[3].equals("p")){
        Resept r = doc.skrivPResept(medisin, pasIent, Integer.parseInt(biter[4]));
        resepter.leggTil(teller,r);
        teller++;
      }

    }
  }  
  public void skrivTilFil(){
    PrintWriter skriver = null;
    try{
      skriver = new PrintWriter("alldata.txt");
    } catch (Exception e){
      System.out.println("Filen kan ikke opprettes.");
      System.exit(1);
    }
    skriver.println("# Pasienter (navn, fnr)");
    for(Pasient p : pasienter){
      skriver.println(p.hentNavn() + ", " + p.hentFodsel());
    }
    skriver.println("#Legemidler (navn, pris, virkestoff, id)");
    for(Legemiddel l : legemiddeler){
      skriver.println(l.hentNavn() + ", " + l.hentPris() + ", " + l.hentVirkestoff() + ", " + l.hentId());
    }
    skriver.println("#Leger (navn, kontrollID (0 hvis det er en vanlig lege))");
    for(Lege l : leger){
      skriver.println(l.hentNavn() + ", " + l.hentKontrollId());
    }
    skriver.println("#Resepter (legemiddel, utskrivende lege, pasient, reit, id)");
    for(Resept r : resepter){
      skriver.println(r.hentLegemiddel() + ", " + r.hentLege() + ", " + r.hentPasient() + ", " + r.hentReit() + ", " + r.hentId());
    }
    skriver.close();

    System.out.println("\n Alt ble lagret inn i filen! \n");
  }

  public void SkrivUt(){
    System.out.println("oversikt over registrerte pasienter: ");
    for (int i = 0; i < pasienter.stoerrelse(); i++){
      System.out.println(pasienter.hent(i));
    }
    System.out.println(" ");
    System.out.println("oversikt over registrerte legemiddeler: ");
    for (int f = 0; f < legemiddeler.stoerrelse(); f++){
      System.out.println(legemiddeler.hent(f));
    }
    System.out.println(" ");
    System.out.println("oversikt over våre leger: ");
    for (int x = 0; x < leger.stoerrelse(); x++){
      System.out.println(leger.hent(x));
    }
    System.out.println(" ");
    System.out.println("oversikt over våre utskrevne resepter: ");
    for (int j = 0; j < resepter.stoerrelse(); j++){
      System.out.println(resepter.hent(j));
    }

  }
  public void LeggtilElement() throws UlovligUtskrift,UlovligType{
    Scanner sc = new Scanner(System.in);
    System.out.println("for å legge til ny legemiddel, tast A: ");
    System.out.println("for å legge til ny pasient, tast B: ");
    System.out.println("for å legge til ny lege, tast C: ");
    System.out.println("for å legge til ny resept, tast D: ");
    String svar = sc.next();
    if (svar.toUpperCase().equals("A")){
      nyttLegemiddel();
    }
    else if (svar.toUpperCase().equals("B")){
      nyPasient();
    }
    else if (svar.toUpperCase().equals("C")){
      nyLege();
    }
    else if (svar.toUpperCase().equals("D")){
      nyResept();
    }
    else System.out.println("vennligst tast inn en bokstav på formatet A,B,C eller D");
  }

  public void nyPasient(){
    Scanner sc = new Scanner(System.in);
    System.out.println("hva heter pasienten? ");
    String navn = sc.nextLine();
    System.out.println("hva er fødselsdatoen til pasienten? ");
    String fd = sc.next();
    Pasient p = new Pasient(navn, fd);
    pasienter.leggTil(pasienter.stoerrelse(), p);
  }

  public void nyLege(){
    Scanner sc = new Scanner(System.in);

    System.out.println("hva heter legen? ");
    String navn = sc.nextLine();
    System.out.println("tast 1 dersom legen er spesialist, ellers 0");
    int svar = sc.nextInt();
    if (svar==1){
      System.out.println("bestem kontrollId for legen: ");
      int kontroll = sc.nextInt();
      Lege d = new Lege(navn, kontroll);
      leger.leggTil(leger.stoerrelse(), d);
      System.out.println("legen er lagt til! ");
    }
    else if (svar==0){
      Lege d = new Lege(navn, 0);
      leger.leggTil(leger.stoerrelse(), d);
      System.out.println("legen er lagt til! ");
    }
    else System.out.println("vennligst tast inn 1 for spesialist og 0 for valig lege");
  }

  public void nyttLegemiddel(){
    Scanner sc = new Scanner(System.in);
    System.out.println("tast inn navn på legemiddelet: ");
    String navn = sc.nextLine();
    System.out.println("bestem pris for legemiddel, rundet til nermeste hele krone");
    int pris = sc.nextInt();
    System.out.println("bestem virkestoff: ");
    double virkestoff = sc.nextDouble();
    System.out.println("tast a dersom legemiddelet er vanlig, b dersom det er vanedannende, og c dersom det er narkotisk");
    String svar = sc.next();
    if (svar.toLowerCase().equals("a")){
      Vanlig v = new Vanlig(navn, pris, virkestoff);
      legemiddeler.leggTil(legemiddeler.stoerrelse(), v);
      System.out.println(navn + " er lagt til som vanlig legemiddel");
    }
    else if (svar.toLowerCase().equals("b")){
      System.out.println("bestem vanedannende styrke, i helt tall");
      int s = sc.nextInt();
      Vanedannende v = new Vanedannende(navn, pris, virkestoff, s);
      legemiddeler.leggTil(legemiddeler.stoerrelse(), v);
      System.out.println(navn + " er lagt til som vanedannende legemiddel");
    }
    else if (svar.toLowerCase().equals("c")){
      System.out.println("bestem narkotisk styrke: ");
      int s = sc.nextInt();
      Narkotisk n = new Narkotisk(navn, pris, virkestoff, s);
      legemiddeler.leggTil(legemiddeler.stoerrelse(), n);
      System.out.println(navn + " er lagt til som narkotisk legemiddel");
    }
    else System.out.println("vennligst bestem typen ved å taste inn a for vanlig, b for vanedannende og c for narkotisk");
  }

  public void nyResept() throws UlovligUtskrift,UlovligType{
    Scanner sc = new Scanner(System.in);
    System.out.println("tast inn id for denne reseptens pasient: ");
    int id = sc.nextInt();
    Pasient p = hentPasient(id);
    System.out.println("tast inn legemiddel nummer: ");
    int nr = sc.nextInt();
    Legemiddel l = hentLegemiddel(nr);
    System.out.println("navnet på legen som skriver ut denne resepten");
    String navn = sc.nextLine();
    Lege d = hentLege(navn);
    System.out.println("dersom typen på resepten er hvit, tast A: ");
    System.out.println("dersom typen på resepten er blaa, tast B: ");
    System.out.println("dersom typen på resepten er militaer, tast C: ");
    System.out.println("dersom typen på resepten er p-resept, tast D: ");
    String svar = sc.next();
    if (svar.toUpperCase().equals("A")){
      System.out.println("bestem reit: ");
      int reit = sc.nextInt();
      Resept r = d.skrivHvitResept(l,p,reit);
      resepter.leggTil(resepter.stoerrelse(),r);
      System.out.println("den hvite resepten er lagt til");
    }
    else if (svar.toUpperCase().equals("B")){
      System.out.println("bestem reit: ");
      int reit = sc.nextInt();
      Resept r = d.skrivBlaaResept(l,p,reit);
      resepter.leggTil(resepter.stoerrelse(),r);
      System.out.println("den blåe resepten er lagt til");
    }
    else if (svar.toUpperCase().equals("C")){
      Resept r = d.skrivMilResept(l,p);
      resepter.leggTil(resepter.stoerrelse(),r);
      System.out.println("militaer resepten er lagt til");
    }
    else if (svar.toUpperCase().equals("D")){
      System.out.println("bestem reit: ");
      int reit = sc.nextInt();
      Resept r = d.skrivPResept(l,p,reit);
      resepter.leggTil(resepter.stoerrelse(),r);
      System.out.println("p-resepten er lagt til");
    }
    else System.out.println("vennligst tast A for hvit resept, B for blaa, C for militaer og D for p-resept");

  }

  public Legemiddel hentLegemiddel(int nr){
    Legemiddel x = null;
    for (int i = 0; i < legemiddeler.stoerrelse(); i++){
      if (legemiddeler.hent(i).hentId()==nr){
        x = legemiddeler.hent(i);
      }
    }
    return x;
  }
  public Lege hentLege(String navn){
    Lege x = null;
    for (int i = 0; i < leger.stoerrelse(); i++){
      if (leger.hent(i).hentNavn().equals(navn)){
        x = leger.hent(i);
      }
    }
    return x;
  }
  public Pasient hentPasient(int id){
    Pasient x = null;
    for (int i = 0; i < pasienter.stoerrelse(); i++){
      if (pasienter.hent(i).hentId()==id){
        x = pasienter.hent(i);
      }
    }
    return x;
  }

  public void BrukResept(){
    Scanner sc = new Scanner(System.in);
    System.out.println("tast inn id for pasienten du ønsker å se resepter for: ");
    for (int i = 0; i < pasienter.stoerrelse(); i++){
      System.out.println(pasienter.hent(i));
    }
    int pasientid = sc.nextInt();
    Pasient p = hentPasient(pasientid);
    System.out.println("valgt pasIent: " + p);
    System.out.println(" ");
    System.out.println("tast inn id for resepten du ønsker å bruke: ");

    IndeksertListe<Resept> x = p.pasientens_resepter();
    for (int j = 0; j < x.stoerrelse(); j++){
      System.out.println(x.hent(j));
    }
    int reseptid = sc.nextInt();
    for (int I = 0; I < x.stoerrelse(); I++){
      Resept r = x.hent(I);
      if (r.hentId()==reseptid){
        if (r.bruk() == true){
          System.out.println("brukte resept på " + r.hentLegemiddel().hentNavn() + ". antall gjenværende reit: " + r.hentReit());
        }
        else System.out.println("kunne bruke resepte på " + r.hentLegemiddel().hentNavn() + ", fordi det er ingen gjenværende reit");
      }
    }


  }




}
