package lineales.dinamicas;

public class Cola {
    private Nodo fin;
    private Nodo frente;
    
    public Cola(){
        this.fin = null;
        this.frente = null;
    }
    public boolean poner(Object objIn) {
        Nodo temp = new Nodo(objIn,null);
        if (esVacia()) {
            this.frente = temp;
            fin = this.frente;
        } else {
            this.fin.setEnlace(temp);
            this.fin = temp; 
        }
        return true;
    }
    public boolean sacar() {
        boolean exito = false;
        if (!esVacia()) { 
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            exito = true;
        }
        return exito;
    }
    public Object obtenerFrente() {
        Object retorna = null;
        if (!esVacia()) {
            retorna = this.frente.getElem();
        }
        return retorna;
    }
    public boolean esVacia() {
        return (this.fin == null & this.frente == null);
    }
    public void vaciar() {
        fin = null;
        while (!esVacia()) {
            frente = frente.getEnlace();
        }
    }
    public Cola clone() {
        Cola colaClon = new Cola();
        if (!this.esVacia()) {
            colaClon.frente = new Nodo (this.frente.getElem(),null);
            cloneAux(colaClon.frente,this.frente.getEnlace());
            colaClon.fin = new Nodo(fin.getElem(),null);
        }
        return colaClon;
    }
    private void cloneAux(Nodo nodoTemp,Nodo nodoEnlace) {  
        if (nodoEnlace != null) {
            nodoTemp.setEnlace(new Nodo(nodoEnlace.getElem(),null));
            cloneAux(nodoTemp.getEnlace(), nodoEnlace.getEnlace());
        }

    }
    public String toString() {
        String cadena = "[|";
        Nodo temp = this.frente;
        while (temp != null) {
            cadena = cadena + temp.getElem() + "|";
            temp = temp.getEnlace();
        }
        cadena = cadena + "]";
        return cadena;
    }
}