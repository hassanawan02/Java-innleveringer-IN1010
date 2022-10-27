import java.lang.*;
import java.util.*; 

class IndeksertListe <T> extends Lenkeliste<T> implements Liste<T> {


  Node start;

  class ListeIterator implements Iterator<T>{
    Node peker = start;
    @Override
    public T next(){
      T x = peker.data;
      peker = peker.next;
      return x;
    }
    @Override
    public boolean hasNext(){
      return peker.data!=null;
    }


  }


  public void leggTil (int pos, T x)  {

    Node nytt = new Node(x);
    Node peker = start;

    if (start==null) {
      nytt.next = start;
      start = nytt;
      return;
    }

    for (int i = 0; i < pos-1; i++){
      if (peker.next !=null){
        peker = peker.next;
      }
    }
    nytt.next = peker.next;
    peker.next = nytt;
}


  public T hent(int pos) {
    if (start==null || pos >= stoerrelse()){
      throw new UgyldigListeindeks(pos);
    }

    if (pos == 0)
    {
      return start.data;
    }

    Node peker = start;
    for (int i = 0; i<pos; i++){
      peker = peker.next;
    }
    return peker.data;
}

  public T fjern(int pos) {
    if (start == null || pos >= stoerrelse()){
      throw new UgyldigListeindeks(pos);
    }
    if (pos == 0) {

      T x = start.data;
      start = start.next;
      return x;
    }

    else {
      Node peker = start;
      Node n1 = null;

      for (int i = 0; i<pos-1; i++){
        peker = peker.next;
      }
      T y = peker.next.data;
      n1 = peker.next;
      peker.next = n1.next;
      return y;

    }

  }
  public void sett(int pos, T x){
    Node nytt = new Node(x);
    if (start == null || pos >= stoerrelse()){
      throw new UgyldigListeindeks(pos);
    }
    if (pos==0){
      start = start.next;
      nytt.next = start;
      start = nytt;
      return;
    }
    fjern(pos);
    leggTil(pos,x);
  }
  public int stoerrelse(){
    if (start == null){
      return 0;
    }
    Node peker = start;
    int count = 0;
    while(peker.next!=null){
      peker = peker.next;
      count++;
    }
    return count+1;
  }

  public Iterator<T> iterator(){
    return new ListeIterator();
  }
  public void print(){
    if (start == null){
      System.out.println("tom liste; ");
    }
    else {
      Node peker = start;
      while (peker!=null){
        System.out.println(peker.data);
        peker = peker.next;
      }

    }
  }


}
