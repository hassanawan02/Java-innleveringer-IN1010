import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
public class Oblig5Del1 {
    private static SubsekvensRegister subregister = new SubsekvensRegister();

    public static void main(String[] args){
        final String datamappe = args[0];
        final String metadata = args[1];

        lesData(datamappe, metadata);
        alleHash();
        flestForekomster();
    }

    private static void lesData(String mappe, String data){
        // Prøver å finne veien fra mappe til fil
        try{
            Scanner input = new Scanner(new File(mappe + "/" + data));
            while(input.hasNextLine()){
                String line = input.nextLine().strip();
                HashMap<String,Subsekvens> hash = SubsekvensRegister.konvTilSubsekvens(mappe + "/" + line);
                subregister.settInnHashMap(hash);

            }
        } catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet.");
        }
    }
    private static void alleHash(){
        if(subregister.hentAntall() > 1){
            HashMap<String,Subsekvens> hash = SubsekvensRegister.slaSammen(subregister.hentUt(), subregister.hentUt());
            subregister.settInnHashMap(hash);
            alleHash();
        }
    }
    private static void flestForekomster(){
        if(subregister.hentAntall() == 1){
            HashMap<String,Subsekvens> hash = subregister.hentUt();
            int total = 0;
            for(Subsekvens subsekvens : hash.values()){
                if(subsekvens.hentAntall() > total){
                    total = subsekvens.hentAntall();
                }
                if(subsekvens.hentAntall() == total){
                    System.out.println(subsekvens);
                }
            }
        }
    }

}
