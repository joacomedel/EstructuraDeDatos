package conjuntista;

public class NodoArbol {
    private Comparable elem;
    private NodoArbol izq;
    private NodoArbol der;
    public NodoArbol(Comparable elem){
        this.elem = elem;
    }
    public void setDer(NodoArbol der) {
        this.der = der;
    }
    public void setElem(Comparable elem) {
        this.elem = elem;
    }
    public void setIzq(NodoArbol izq) {
        this.izq = izq;
    }
    public NodoArbol getDer() {
        return der;
    }
    public Comparable getElem() {
        return elem;
    }
    public NodoArbol getIzq() {
        return izq;
    }
}
