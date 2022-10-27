abstract class Lenkeliste <T> implements Liste<T>{
    protected Node forst = null;
    protected Node sist = null;
    protected int stoerrelse = 0;
    public class Node{
        Node neste = null;
        Node forrige = null;
        T data;
        Node(T x){
            data = x;
            stoerrelse++;
        }
    }
    public int stoerrelse(){
        return stoerrelse;
    }
    @Override
    public void leggTil(T x){
        Node nyNode = new Node(x);
        if(forst == null){
            forst = nyNode;
        } else if(sist == null){
            sist = nyNode;
            sist.forrige = forst;
            forst.neste = sist;
        }else{
            sist.neste = nyNode;
            nyNode.forrige = sist;
            sist = nyNode;
        }

        sist = nyNode;
    }
    @Override
    public T hent()throws UgyldigListeindeks{
        if (forst == null){
            throw new UgyldigListeindeks(0);
        }
        return forst.data;
    }
    @Override
    public T fjern() throws UgyldigListeindeks{

        if(stoerrelse == 0){
            throw new UgyldigListeindeks(0);
        }
        T nodeFjernet = forst.data;
        if(stoerrelse <= 0){
            return null;
        }
        if(stoerrelse == 1){
            forst = null;
            sist = null;
        }else{
            forst.neste.forrige = null;
            forst = forst.neste;
        }
        
        stoerrelse -= 1;
        return nodeFjernet;
    }
    @Override
    public String toString(){
        if (forst == null){
            return("Ingen elementer i listen");
        }
        Node valgt = forst;
        String svarstreng = "";
        for(int i = 0; i < stoerrelse; i++){
            svarstreng = svarstreng + valgt.data + "\n";
            valgt = valgt.neste;
        }
        return svarstreng;
    }

}