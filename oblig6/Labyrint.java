import java.util.Scanner;
import java.io.*;

public class Labyrint {
    private int antRader, antKolonner;
    private Rute[][] rute;
    public Labyrint(String filnavn) throws FileNotFoundException{
        Scanner input = new Scanner(new File(filnavn));
        String[] stoerrelse = input.nextLine().strip().split(" ");
        int radnr = 0;
        antRader = Integer.parseInt(stoerrelse[0]);
        antKolonner = Integer.parseInt(stoerrelse[1]);
        rute = new Rute[antRader][antKolonner];
        // setter opp teller for linjenummer
        int linjenummer = 1;
        while (input.hasNextLine()) {
            String[] linje = input.nextLine().strip().split("");
            for (int i = 0; i < linje.length; i++) {
                Rute nyrute = null;
                // sjekker forste og siste linje for aapninger
                if (linjenummer == 1 || linjenummer == antRader + 1) {
                    if (linje[i].equals(".")) nyrute = new Aapning(radnr, i, this);
                    else if (linje[i].equals("#")) nyrute = new SortRute(radnr, i, this); 
                }

                else {
                    if (linje[i].equals(".") && (i == linje.length - 1 || i == 0)) nyrute = new Aapning(radnr, i, this);
                    else if (linje[i].equals(".")) nyrute = new HvitRute(radnr, i, this);
                    else if (linje[i].equals("#")) nyrute = new SortRute(radnr, i, this);
                }

                rute[radnr][i] = nyrute;
            }
            linjenummer++;
            radnr++;
        }
        for(int i = 0; i < antRader; i++){
            for(int x = 0; x < antKolonner; x++){
                Rute nord = null;
                Rute syd = null;
                Rute vest = null;
                Rute oest = null;
                // Starter med å sjekke nord, så sjekker vi resten
                if(i - 1 >= 0){
                    nord = rute[i - 1][x];
                }
                if(i + 1 <= antRader - 1){
                    syd = rute[i + 1][x];
                }
                if(x - 1 >= 0){
                    vest = rute[i][x - 1];
                }
                if(x + 1 <= antKolonner - 1){
                    oest = rute[i][x + 1];
                }
                rute[i][x].naboer(nord, syd, vest, oest);
            }
        }

    }
    public Rute hentRute(int radnummer, int kolonnenummer){
        return rute[radnummer][kolonnenummer];
    }
    @Override
    public String toString(){
        String utskrift = "";
        for(int i = 0; i < antRader; i++){
            for(int x = 0; x < antKolonner; x++){
                char rep = rute[i][x].hentKarakter();
                utskrift += Character.toString(rep);
            }
            if(i < antRader - 1){
                utskrift += "\n";
            }
        }
        return utskrift;
    }
    public void finnUtVeiFra(int rad, int kol){
        if(rute[rad][kol] instanceof SortRute){
            System.out.println("Ikke mulig aa starte i sort rute.");
            return;
        }
        rute[rad][kol].finn(null);
    }
    
}
