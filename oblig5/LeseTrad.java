import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class LeseTrad implements Runnable{
    private final String filnavn;
    private Monitor2 monitor;
    private CountDownLatch blokk;

    public LeseTrad(String filnavn, Monitor2 monitor, CountDownLatch blokk){
        this.filnavn = filnavn;
        this.monitor = monitor;
        this.blokk = blokk;
    }

    @Override
    public void run(){
        System.out.println("Started lesetrad");
        HashMap<String,Subsekvens> filHash = Monitor2.konvTilSubsekvens(this.filnavn);
        monitor.settInnHashMap(filHash);
        blokk.countDown();
    }
}
