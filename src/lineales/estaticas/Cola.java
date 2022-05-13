package lineales.estaticas;

public class Cola {
    private Object[] arreglo;
    private int frente; 
    private int fin;
    private static final int TAMANIO = 10;

    public Cola(){
    this.arreglo = new Object[this.TAMANIO];
    this.frente =0;
    this.fin = 0;    
    }
    public boolean poner(Object objIn) {
        boolean exito = false;
        if (!esllena()){
            this.arreglo[this.fin] = objIn;
            this.fin = (this.fin+1)%TAMANIO;
            exito = true;
        }
        return exito;
    }
    public boolean sacar() {
        boolean exito = false;
        if (!esVacia()) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente+1)%TAMANIO;
            exito = true;
        }
        return exito;
    }
    public Object obtenerFrente(){
        Object obj = null;
        if (!esVacia()) {
            obj = this.arreglo[frente];
        }
        return obj;
    }
    public boolean esVacia() {
        return this.fin == this.frente;
    }
    private boolean esllena(){
        return (fin+1)%TAMANIO==frente;
    }
    public void vaciar() {
        while (!esVacia()){
            this.arreglo[this.frente] = null;
            this.frente = (this.frente+1)%TAMANIO;
        }
    }
    public Cola clone() {
        Cola colaClon = new Cola();
        if(!esVacia()){
            int i = this.frente;
            while (i != this.fin) {
                colaClon.arreglo[i]=this.arreglo[i];
                i=(i+1)%10;
            }
        }
        colaClon.frente = this.frente;
        colaClon.fin= this.fin;        
        return colaClon;
    }
    public String toString() {
        int i = this.frente;
        String cadena ="[|";
        while (i != fin) {
            cadena = cadena + this.arreglo[i] + "|";
            i=(i+1)%10;
        }
        cadena = cadena + "]";
        return cadena;
    }
}
