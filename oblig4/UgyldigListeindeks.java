
import java.io.*;


class UgyldigListeindeks extends RuntimeException {
    UgyldigListeindeks (int indeks) {
        super("Ugyldig indeks: "+indeks);
    }
}
