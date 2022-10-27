public class Returverdi<T> {
    private final T verdi1;
    private final T verdi2;

    public Returverdi(T verdi1, T verdi2){
        this.verdi1 = verdi1;
        this.verdi2 = verdi2;
    }
    public T hentVerdi1(){
        return verdi1;
    }
    public T hentVerdi2(){
        return verdi2;
    }
}
