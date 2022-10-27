import java.util.HashMap;
import java.util.Scanner; 
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class Monitor2 extends SubsekvensRegister{
    private static Lock las = new ReentrantLock(true);
    private Condition ikkeKlar = las.newCondition();
    private final int krav = 2;


    @Override
    public void settInnHashMap(HashMap<String,Subsekvens> hash){
        las.lock();
        try{
            hashbeholder.add(hash);
            if(hentAntall() >= krav){
                ikkeKlar.signalAll();
            }
            System.out.println("Hashmapen ble satt inn av lesetrad");
        }finally{
            las.unlock();
        }
    }
    public void settInnFlett(HashMap<String,Subsekvens> hash){
        hashbeholder.add(hash);
        if(hentAntall() >= krav){
            ikkeKlar.signalAll();
        }
        System.out.println("Satte inn flettet hashmap");
    }
    public Returverdi<HashMap<String,Subsekvens>> hentToHash() throws InterruptedException{
        las.lock();
        try{
            while(hentAntall() < krav){
                ikkeKlar.await();
            }
            System.out.println("Hentet ut to hashmaps fra flettetrad");
            return new Returverdi<HashMap<String,Subsekvens>>(hentUt(), hentUt());
        }finally{
            las.unlock();
        }
    }
    public void slaSammenToHash(HashMap<String,Subsekvens> hash, HashMap<String,Subsekvens> hash1){
        las.lock();
        try{
            HashMap<String,Subsekvens> nyhash = new HashMap<>();

            for(String noekkel : hash1.keySet()){
                nyhash.put(noekkel, new Subsekvens(1, noekkel));
            }
            for(String noekkel1 : hash.keySet()){
                Subsekvens subsekvens = hash.get(noekkel1);
                int teller = subsekvens.hentAntall();
                for(String noekkel2 : hash1.keySet()){
                    Subsekvens subsekvens1 = hash1.get(noekkel2);
                    int antall = subsekvens1.hentAntall();
                    if(noekkel1.equals(noekkel2)){
                        teller += antall;
                    }

                    nyhash.put(noekkel1, new Subsekvens(teller, noekkel1));
                }
            }
            System.out.println("To kart slatt sammen av lesetrad");
            settInnFlett(nyhash);
        }finally{
            las.unlock();
        }
    }
    public static HashMap<String,Subsekvens> konvTilSubsekvens(String filnavn){
        las.lock();
        try{
            HashMap<String,Subsekvens> subsekvenser = new HashMap<>();

            try{
                Scanner input = new Scanner(new File(filnavn));
                int teller = 0;
                while (input.hasNextLine()) {
                    String sekvens = input.nextLine();
                    for (int i = 0; i < sekvens.length(); i++) {
                        teller++;
                        if (teller <= sekvens.length() - 2) {
                            String subsekvens = "";
                            char del1 = sekvens.charAt(i);
                            subsekvens += del1;
                            char del2 = sekvens.charAt(i + 1);
                            subsekvens += del2;
                            char del3 = sekvens.charAt(i + 2);
                            subsekvens += del3;
                            subsekvenser.put(subsekvens, new Subsekvens(1, subsekvens));
                        }
                    }
                    teller = 0;
                }
            }catch(FileNotFoundException e){
                System.out.println("Fil ikke funnet");
            }
            System.out.println("Konvertert til subsekvenser");
            return subsekvenser;
        }finally{
            las.unlock();
        }
    }
}


