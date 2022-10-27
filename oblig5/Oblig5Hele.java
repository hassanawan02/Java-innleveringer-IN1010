import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.io.*;

public class Oblig5Hele {
    private static Monitor2 syk = new Monitor2();
    private static Monitor2 frisk = new Monitor2();

    private static File datamappe;
    private static int antFiler;

    private static int antSykdom = 0;
    private static int antIkkeSykdom = 0;

    private static CountDownLatch blokk1;
    private static CountDownLatch blokk2;

    public static void main(String[] args) throws InterruptedException{
        final String mappe = args[0];
        final String metadata = args[1];

        setup(mappe, metadata);
        init(mappe, metadata);
        await();
        sammenlignData();
    }
    private static void setup(String mappe, String metafil){
        datamappe = new File(mappe);
        antFiler = datamappe.list().length - 1;
        try{
            Scanner input = new Scanner(new File(mappe + "/" + metafil));
            while(input.hasNextLine()){
                String[] line = input.nextLine().strip().split(",");
                if(line[1].equals("True")){
                    antSykdom++;
                }
                else if(line[1].equals("False")){
                    antIkkeSykdom++;
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        final int antFlettetTrader;

        if(antSykdom > 1 && antIkkeSykdom > 1){
            antFlettetTrader = antFiler - 2;
        }
        else{
            antFlettetTrader = antFiler - 1;
        }
        blokk1 = new CountDownLatch(antFiler);
        blokk2 = new CountDownLatch(antFlettetTrader);
    }
    private static void init(String mappe, String metadata){
        try {
            Scanner input = new Scanner(new File(mappe + "/" + metadata));
            while(input.hasNextLine()) {
                String[] line = input.nextLine().split(",");

                if(line[1].equals("True")) {
                    Runnable leseTrad = new LeseTrad(mappe + "/" + line[0], syk, blokk1);
                    Thread trad = new Thread(leseTrad);
                    trad.start();
                }
                else{
                    Runnable leseTrad = new LeseTrad(mappe + "/" + line[0], frisk, blokk1);
                    Thread trad = new Thread(leseTrad);
                    trad.start();
                }
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        if(antSykdom > 1){
            for (int i = 0; i < antSykdom - 1; i++) {
                Runnable fletteTrad = new FletteTrad(syk, blokk2, antSykdom);
                Thread trad = new Thread(fletteTrad);
                trad.start();
            }
        }
        else{
            Runnable fletteTrad = new FletteTrad(syk, blokk2, antSykdom);
            Thread trad = new Thread(fletteTrad);
            trad.start();
        }
        if(antIkkeSykdom > 1){
            for (int i = 0; i < antIkkeSykdom - 1; i++) {
                Runnable fletteTrad = new FletteTrad(frisk, blokk2, antIkkeSykdom);
                Thread trad = new Thread(fletteTrad);
                trad.start();
            }
        }
        else{
            Runnable fletteTrad = new FletteTrad(frisk, blokk2, antIkkeSykdom);
            Thread trad = new Thread(fletteTrad);
            trad.start();
        }
    }
    private static void await() throws InterruptedException{
        blokk1.await();
        System.out.println("Lesetradene er ferdige med a konvertere og a sette inn hash");
        blokk2.await();
        System.out.println("Alle tradene er ferdig. Programmet er synkronisert.");
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
    private static void sammenlign(Monitor2 sykMonitor, Monitor2 friskMonitor){
        System.out.println("Sammenligner forekomstene ved folk som har sykdommen og de som ikke har hatt sykdommen. \n");
        if(sykMonitor.hentAntall() == 1){
            HashMap<String, Subsekvens> syk = sykMonitor.hentUt();
            HashMap<String, Subsekvens> frisk = friskMonitor.hentUt();
            ArrayList<Subsekvens> oftest = new ArrayList<>();
            int ant = 0;
            for (Subsekvens subsekvens : syk.values()){
                if (subsekvens.hentAntall() > ant){
                    ant = subsekvens.hentAntall();
                }
            }
            for (Subsekvens subsekvens : syk.values()){
                if (subsekvens.hentAntall() == ant){
                    oftest.add(subsekvens);
                }
            }

            ArrayList<Subsekvens> remove = new ArrayList<>();

            for (Subsekvens e : oftest){
                for (Subsekvens subsekvens : frisk.values()){
                    if (e.hentSubsekvens().equals(subsekvens.hentSubsekvens())) {
                        remove.add(e);
                    }
                }
            }

            oftest.removeAll(remove);

            for(Subsekvens subsekvens : oftest){
                System.out.println(subsekvens);
            }
        }
    }
    private static void sammenlignData(){
        System.out.println("Sammenligner forekomstene ved folk som har sykdommen og de som ikke har hatt sykdommen \n");
        System.out.println(syk.hentAntall());
        System.out.println(frisk.hentAntall());
        if(syk.hentAntall() == 1){
            HashMap<String,Subsekvens> hashSyk = syk.hentUt();
            HashMap<String,Subsekvens> hashFrisk = frisk.hentUt();
            ArrayList<Subsekvens> forekomstene = new ArrayList<>();
            final int limit = 7;

            for(Subsekvens subsekvensSyk : hashSyk.values()){
                for(Subsekvens subsekvensFrisk : hashFrisk.values()){
                    if(subsekvensSyk.hentSubsekvens().equals(subsekvensFrisk.hentSubsekvens())){
                        if(subsekvensSyk.hentAntall() - subsekvensFrisk.hentAntall() >= limit){
                            forekomstene.add(subsekvensSyk);
                        }
                    }
                }
            }
            System.out.println("Subsekvenser hvor forekomstene av sykdommen hos pasientene er 7 eller fler, enn pasienter som ikke har hatt sykdommen. \n");
            for(Subsekvens subsekvens : forekomstene){
                System.out.println(subsekvens);
            }
        }
    }
}
