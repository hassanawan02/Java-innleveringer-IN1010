public class Stabel<T> extends Lenkeliste<T> {
    @Override
    public void leggTil(T x){
        Node ny = new Node(x);

        if(stoerrelse == 0){
            forst = ny;
            sist = ny;
        }
        else {
            ny.neste = forst;
            forst.forrige = ny;
            forst = ny;
        }
        stoerrelse ++;
    }
}
