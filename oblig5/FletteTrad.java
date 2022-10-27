import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
public class FletteTrad implements Runnable{
    private Monitor2 monitor;
    private CountDownLatch blokk;
    private int antFiler;

    public FletteTrad(Monitor2 monitor, CountDownLatch blokk, int antFiler){
        this.monitor = monitor;
        this.blokk = blokk;
        this.antFiler = antFiler;
    }

    @Override
    public void run(){
        try{
            System.out.println("Flettetrad startet");
            if(antFiler > 1){
                Returverdi<HashMap<String,Subsekvens>> retur = monitor.hentToHash();
                monitor.slaSammenToHash(retur.hentVerdi1(), retur.hentVerdi2());
            }
            blokk.countDown();
            System.out.println("Flettetrad teller: " + blokk.getCount());
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
