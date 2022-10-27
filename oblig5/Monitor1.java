import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;
public class Monitor1 extends SubsekvensRegister{
    private static Lock las;

    public Monitor1(){
        las = new ReentrantLock(true);
    }

    @Override
    public void settInnHashMap(HashMap<String,Subsekvens> hash){
        las.lock();
        try{
            hashbeholder.add(hash);
        }finally{
            las.unlock();
        }
    }
    public static HashMap<String,Subsekvens> konvTilSubsekvens(String filnavn){
        las.lock();
        try{
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
            }catch(FileNotFoundException e){
                System.out.println("Fil ikke funnet");
            }
            return subsekvens;
        }
        finally {
            las.unlock();
        }
    }
}
