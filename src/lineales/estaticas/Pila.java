package lineales.estaticas;
public class Pila {
    //Atributos
    private Object [] arreglo;
    private static final int tamanio = 10; 
    private int tope;
    //Constructor
    public Pila(){
    this.arreglo = new Object[tamanio];
    this.tope = -1;
    }
    public boolean apilar(Object objetoIn) {
        boolean exito = false; //Si no entra en el if mandamos falso
        if (!(this.tope+1 >= this.tamanio)) { //Verifica si esta lleno , si no esta lleno entra
            this.tope++;//aumentamos el tope
            this.arreglo[tope]=objetoIn; //colocamos en el nuevo tope aumentado el objeto
            exito = true; 
        }
        return exito;
    }
    public boolean desapilar() {
        boolean exito = false;//Si no entra en el if mandamos falso
        if (!this.esVacia()) { //Verificamos si no es vacia
            this.arreglo[this.tope] = null; //vaciamos
            this.tope--; //restamos
            exito = true; 
        }
        return exito;
    }
    public Object obtenerTope() {
        Object obj; //creamos un objeto para los dos casos del if
        if (this.esVacia()) { 
            obj= null; //Si es vacia devolvemos null 
        }else{
            obj = this.arreglo[tope]; //Si no es vacia devolvemos el objeto correspondiente
        }
        return obj;
    }
    public boolean esVacia() {
        return this.tope == -1; //verificamos si es vacia
    }
    public void vaciar() {
        for (int i = tope; i >= 0; i--) {
            this.desapilar();//desapilamos hasta que lleguemos a -1
        }
    }
    public Pila clone() {
        Pila pilaOut;
        pilaOut =new Pila();//creamos la pila para crear el arreglo
        int i = 0;
        pilaOut.tope= this.tope;
        while (i<=this.tope) {
            pilaOut.arreglo[i] = this.arreglo[i];
            i++;
        }
        return pilaOut;
    }
    public String toString() {
        String cadena;
        cadena = "(|" ;
        int i =  0;
        while (i <= this.tope) {
            cadena = cadena + this.arreglo[i] + "|";
            i++;
        }
        cadena = cadena + ")";
        return cadena;
    }
    
}

