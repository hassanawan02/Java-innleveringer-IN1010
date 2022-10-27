public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {
    
    @Override
    public void leggTil(T x){
        Node nyNode;
        Node navaerende = forst;

        if(stoerrelse == 0){
            super.leggTil(x);
            return;
        }

        for(int i = 0; i < stoerrelse; i++){
            if(x.compareTo(navaerende.data) < 0){
                nyNode = new Node(x);
                nyNode.neste = navaerende;
                nyNode.forrige = navaerende.forrige;
                navaerende.forrige = nyNode;
            
                if(navaerende != forst){
                    nyNode.forrige.neste = nyNode;
                }else{
                    forst = nyNode;
                }
                return;
            }     
        }
        super.leggTil(x);
    }
    public T hent(int pos){
        Node node = forst;
        if(pos < 0 || pos > (stoerrelse - 1)){
            throw new UgyldigListeindeks(pos);
        }else{
            for(int i = 0; i < pos; i++){
                node = node.neste;
            }
        }
        return node.data;
    }
    public T fjern(int pos, T x){
        Node ny = forst;
        if(pos < 0 || pos > (stoerrelse - 1)){
            throw new UgyldigListeindeks(pos);
        }
        for(int i = 0; i < pos; i++){
            ny = ny.neste;
        }
        if(pos == 0){
            forst = forst.neste;
        }else{
            if(pos == stoerrelse-1){
                ny.forrige.neste = null;
            }
        }
        stoerrelse--;
        return ny.data;
    }
    
}
