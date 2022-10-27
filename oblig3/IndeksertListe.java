class IndeksertListe<T> extends Lenkeliste<T>{
    public void leggTil(int pos, T x)throws UgyldigListeindeks{ 

        if(pos > stoerrelse() || pos < 0){ 
            throw new UgyldigListeindeks(pos);
        }
        Node nyNode = new Node(x);
        Node navaerende = forst;
        if(stoerrelse == 0){
            forst = nyNode;
            sist = nyNode;
        }
        else if(pos == 0){
            nyNode.neste = forst;
            forst = nyNode;
        }else if(pos == stoerrelse){
            sist.neste = nyNode;
            sist = nyNode;
        }else{
            int tell = 0;
            while(tell < pos-1){
                tell+=1;
                navaerende = navaerende.neste;
            }
            nyNode.neste = navaerende.neste;
            navaerende.neste = nyNode;
        }
    }
    public void sett(int pos, T x){
        if(pos < 0 || pos >= stoerrelse()){
            throw new UgyldigListeindeks(pos);
        }
        Node nyNode = forst;
        int tell = 0;
        if(pos == 0 && nyNode != null){
            forst.data = x;
        }
        while(nyNode != null){
            if(tell == pos){
                nyNode.data = x;
            }
        tell++;
        nyNode = nyNode.neste;
        }
    } 
    public T hent(int pos){

        if (pos < 0 || pos > (stoerrelse()-1)){
            throw new UgyldigListeindeks(pos);
        }
        Node navaerende = forst;
        for(int i = 0; i < pos; i++){
            navaerende = navaerende.neste;
        }
        return navaerende.data;
    }
    public T fjern(int pos){

        if(pos < 0 || pos >= stoerrelse){
            throw new UgyldigListeindeks(pos);
        }
        Node navaerende = forst;
        T returner;
        int tell = 0;
        while(navaerende != null){
            if(tell == pos){
                stoerrelse -= 1;
                returner = navaerende.data;
                navaerende = navaerende.neste;
                return returner;
            }
            navaerende = navaerende.neste;
            tell += 1;
        }
        return null;
    }
    public String toString(){
        String svarstreng = "";
        Node navaerende = forst.neste;
        for(;1 < stoerrelse;){
            svarstreng = svarstreng + navaerende.data;
            navaerende = navaerende.neste;
        }
        return svarstreng;
    }
}
