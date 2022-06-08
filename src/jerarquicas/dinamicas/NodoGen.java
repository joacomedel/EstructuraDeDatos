package jerarquicas.dinamicas;

public class NodoGen {
    private NodoGen hijoIzq;
    private NodoGen hermanoDer;
    private Object elem;
    public NodoGen(Object elemIn ){
        this.elem = elemIn;
        hijoIzq = null;
        hermanoDer = null;
    }
    public void setElem(Object elem) {
        this.elem = elem;
    }
    public void setHermanoDer(NodoGen hermanoDer) {
        this.hermanoDer = hermanoDer;
    }
    public void setHijoIzq(NodoGen hijoIzq) {
        this.hijoIzq = hijoIzq;
    }
    public Object getElem() {
        return elem;
    }
    public NodoGen getHermanoDer() {
        return hermanoDer;
    }
    public NodoGen getHijoIzq() {
        return hijoIzq;
    }
}
