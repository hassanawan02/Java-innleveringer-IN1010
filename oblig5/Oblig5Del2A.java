import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Del2A {
    private static Monitor1 monitor = new Monitor1();
    public static void main(String[] args) throws InterruptedException{
        final String mappe = args[0];
        final String metadata = args[1];

        File mappen = new File(mappe);
        int antFiler = mappen.list().length - 1;
        // Lager en "blokk" eller "vegg" for at vi kan klarere se når trådene er ferdig.
        CountDownLatch blokk = new CountDownLatch(antFiler);
        lesData(mappe, metadata, blokk);
        blokk.await();
        System.out.println("Ferdig");
        alleHash();
        System.out.println("Subsekvensen som har flest forekomster: ");
        flestForekomster();
    }
    private static void lesData(String mappen, String metadata, CountDownLatch blokk) throws InterruptedException{
        try{
            Scanner input = new Scanner(new File(mappen + "/" + metadata));
            while(input.hasNextLine()){
                String line = input.nextLine().strip();
                Runnable leseTrad = new LeseTrad(mappen + "/" + line, monitor, blokk);
                Thread trad = new Thread(leseTrad);
                trad.start();
            }
        }catch(FileNotFoundException e){
            System.out.println("Fil ikke funnet");
        }
    }
    private static void alleHash(){
        if(monitor.hentAntall() > 1){
            HashMap<String,Subsekvens> hash = SubsekvensRegister.slaSammen(monitor.hentUt(), monitor.hentUt());
            monitor.settInnHashMap(hash);
            alleHash();
        }
    }
    private static void flestForekomster(){ 
        if(monitor.hentAntall() == 1){
            HashMap<String,Subsekvens> hash = monitor.hentUt();
            int ant = 0;
            for(Subsekvens subsekvens : hash.values()){
                if(subsekvens.hentAntall() > ant){
                    ant = subsekvens.hentAntall();
                }
            }    
            for(Subsekvens subsekvens : hash.values()){
                if(subsekvens.hentAntall() == ant){
                    System.out.println(subsekvens);
                }
            }
        }
    }
    
}
