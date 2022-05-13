package jerarquicas.dinamicas;

public class NodoArbol {
    private Object elem ;
    private NodoArbol[] hijos;

    public NodoArbol(Object elemIn , NodoArbol izq , NodoArbol der){
        this.hijos = new NodoArbol[2];
        this.elem = elemIn;
        hijos[0]= izq;
        hijos[1]= der;
    }
    public NodoArbol(Object elemIn ){
        this.hijos = new NodoArbol[2];
        this.elem = elemIn;
        hijos[0]= null;
        hijos[1]= null;
    }
    public Object getElem() {
        return this.elem;
    }
    public void setElem(Object elemIn) {
        this.elem = elemIn;
    }
    public NodoArbol getHijo (int posc){
        return hijos[posc];
    }
    public void setHijo (int posc, NodoArbol nodoIn){
        hijos[posc]= nodoIn;
    }
    public String toString() {
        return "" + elem;
    }
    /*public NodoArbol getIzquierdo() {
        return hijos[0];
    }
    public NodoArbol getDerecho() {
        return hijos[1];
    }
    public void setIzquierdo(NodoArbol nodoIn) {
        hijos[0] = nodoIn;
    }
    public void setDerecho(NodoArbol nodoIn) {
        hijos[1] = nodoIn;
    }*/
}
