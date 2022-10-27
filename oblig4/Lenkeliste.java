import java.lang.*;
import java.util.*; 

abstract class Lenkeliste <T> implements Liste<T> {

  public class Node {
    public T data;
    public Node next;
    public Node(T data){
      this.data = data;
      next = null;
    }
    public T hentData(){
      return this.data;
    }
  }

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
  public Iterator<T> Iterator(){
    return new ListeIterator();
  }




  @Override
  public void leggTil(T x){
    Node nytt = new Node(x);
    if (start == null){
      start = nytt;
      return;
    }
    else {
      Node peker = start;
      while (peker.next != null){
        peker = peker.next;

      }
      peker.next = nytt;
    }
  }


  public boolean contains(T a){
    if (start.data.equals(a)){
      return true;
    }
    Node peker = start;
    while (peker.next != null){
      if (peker.next.data.equals(a)){
        return true;
      }
      peker = peker.next;
    }
    return false;

  }
  @Override
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

  @Override
  public T hent(){
    if (start == null){
      throw new UgyldigListeindeks(0);
    }
    return start.data;
  }
  @Override
  public T fjern(){
    if (start == null){
      throw new UgyldigListeindeks(0);
    }
    if (start == null){
      return start.data;
    }

    T x = start.data;
    start = start.next;
    return x;

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
