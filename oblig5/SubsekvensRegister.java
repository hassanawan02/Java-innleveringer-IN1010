import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;
import java.io.*;
public class SubsekvensRegister {

    protected ArrayList<HashMap<String,Subsekvens>> hashbeholder = new ArrayList<>();
    // private Lock las = new ReentrantLock();
    public void settInnHashMap(HashMap<String,Subsekvens> nyhash){
        hashbeholder.add(nyhash);
    }
    public HashMap<String,Subsekvens> hentUt(){
        HashMap<String,Subsekvens> nyhash = hashbeholder.size() > 0 ? hashbeholder.get(0) : null;
        hashbeholder.remove(0);
        return nyhash;
    }
    public int hentAntall(){
        return hashbeholder.size();
    }
    public static HashMap<String,Subsekvens> konvTilSubsekvens(String filnavn){
        HashMap<String,Subsekvens> subsekvens = new HashMap<>();

        try{
            Scanner input = new Scanner(new File(filnavn));
            int linjenummer = 1;
            while (input.hasNextLine()) {
                String linje = input.nextLine();

                if (linje.length() < 3) {
                    System.out.println(String.format("Linje i subsekvens er ikke 3 karakterer eller lenger.", linjenummer, filnavn));
                    System.exit(1);
                }

                for (int i = 0; i < linje.length()-2; i++) {
                    String subsekvens1 = linje.substring(i, i+3);


                    if (!subsekvens.containsKey(subsekvens1)) {
                        subsekvens.put(subsekvens1, new Subsekvens(1, subsekvens1));
                    }
                }
            }
            input.close();
        } catch(FileNotFoundException e){
            System.out.println("Fant ikke fil");
        }
        return subsekvens;
    }
    public static HashMap<String,Subsekvens> slaSammen(HashMap<String,Subsekvens> hash1, HashMap<String,Subsekvens> hash2){
        HashMap<String,Subsekvens> nyhash = new HashMap<>();

        for(String noekkel1 : hash2.keySet()){
            nyhash.put(noekkel1, new Subsekvens(1, noekkel1));
        }
        for(String noekkel2 : hash1.keySet()){
            Subsekvens subsekvens = hash1.get(noekkel2);
            int teller = subsekvens.hentAntall();
            for(String noekkel3 : hash2.keySet()){
                Subsekvens subsekvens1 = hash2.get(noekkel2);
                int total = subsekvens1.hentAntall();
                if(noekkel2.equals(noekkel3)){
                    teller += total;
                }
                nyhash.put(noekkel2, new Subsekvens(teller, noekkel2));
            }
        }
        return nyhash;
    }

}
