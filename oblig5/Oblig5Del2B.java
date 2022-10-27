import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Del2B {
    public static void main(String[], args) throws InterruptedException{
        final String mappe = args[0];
        final String metadata = args[1];

        File datamappe = new File(mappe);
        int antFiler = datamappe.list().length - 1;

        final int antFlettetTrader = antFiler - 1;

        Monitor2 monitor = new Monitor2();

        // Gjør test for denne delen av oppgaven
        CountDownLatch blokk1 = new CountDownLatch(antFiler);
        CountDownLatch blokk2 = new CountDownLatch(antFlettetTrader);

        lesData(mappe, metadata, monitor, blokk1);
        Runnable fletteTrad = new FletteTrad(monitor, blokk2);
        for(int i = 0; i < antFlettetTrader; i++){
            Thread trad = new Thread(fletteTrad);
            trad.start();
        }

        blokk1.await();
        blokk2.await();

        flestForekomster(monitor);

    }
    private static void lesData(String datamappe, String metafil, Monitor2 monitor, CountDownLatch blokk) throws InterruptedException{
        try{
            Scanner input = new Scanner(new File(datamappe + "/" + metafil));
            while(input.hasNextLine()){
                String line = input.nextLine().strip();
                Runnable leseTrad = new LeseTrad(mappe + "/" + line, monitor, blokk);
                Thread trad = new Thread(leseTrad);
                trad.start();
            }
        }catch(FileNotFoundException e){
            System.out.println("Fil ble ikke funnet!");
        }
    }
    private static void flestForekomster(Monitor2 monitor){
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
